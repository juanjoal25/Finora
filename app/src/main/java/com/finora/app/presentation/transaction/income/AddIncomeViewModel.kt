package com.finora.app.presentation.transaction.income

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finora.app.core.result.Result
import com.finora.app.core.result.toMessage
import com.finora.app.core.validation.Validator
import com.finora.app.domain.model.Category
import com.finora.app.domain.model.Transaction
import com.finora.app.domain.model.TransactionType
import com.finora.app.domain.usecase.auth.GetCurrentUserUseCase
import com.finora.app.domain.usecase.category.GetCategoriesUseCase
import com.finora.app.domain.usecase.transaction.CreateIncomeUseCase
import com.finora.app.domain.usecase.transaction.GetTransactionByIdUseCase
import com.finora.app.domain.usecase.transaction.UpdateTransactionUseCase
import com.finora.app.presentation.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddIncomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val createIncomeUseCase: CreateIncomeUseCase,
    private val updateTransactionUseCase: UpdateTransactionUseCase,
    private val getTransactionByIdUseCase: GetTransactionByIdUseCase,
) : ViewModel() {

    private val editingTransactionId: String? = savedStateHandle[Routes.AddIncome.ARG_TRANSACTION_ID]

    private val _formState = MutableStateFlow(AddIncomeFormState(isEditMode = editingTransactionId != null))
    val formState: StateFlow<AddIncomeFormState> = _formState.asStateFlow()

    private val _uiState = MutableStateFlow<AddIncomeUiState>(AddIncomeUiState.Idle)
    val uiState: StateFlow<AddIncomeUiState> = _uiState.asStateFlow()

    private var currentUserId: String? = null
    private var editingCreatedAt: Instant? = null

    init {
        viewModelScope.launch {
            currentUserId = getCurrentUserUseCase()?.id
            val categories = getCategoriesUseCase(TransactionType.INCOME).first()
            _formState.value = _formState.value.copy(categories = categories)

            val id = editingTransactionId ?: return@launch
            val existing = getTransactionByIdUseCase(id) ?: return@launch
            editingCreatedAt = existing.createdAt
            _formState.value = _formState.value.copy(
                amount = existing.amount.toString(),
                category = categories.firstOrNull { it.name == existing.category },
                date = existing.date,
                description = existing.description.orEmpty(),
            )
        }
    }

    fun onAmountChange(value: String) {
        _formState.value = _formState.value.copy(amount = value, amountError = null)
    }

    fun onCategorySelected(category: Category) {
        _formState.value = _formState.value.copy(category = category, categoryError = null)
    }

    fun onDateChange(date: LocalDate) {
        _formState.value = _formState.value.copy(date = date, dateError = null)
    }

    fun onDescriptionChange(value: String) {
        _formState.value = _formState.value.copy(description = value)
    }

    fun onSaveClick() {
        val state = _formState.value
        val amountError = Validator.validateAmount(state.amount).errorMessageOrNull
        val categoryError = Validator.validateCategory(state.category?.id).errorMessageOrNull
        val dateError = Validator.validateDate(state.date).errorMessageOrNull
        if (amountError != null || categoryError != null || dateError != null) {
            _formState.value = state.copy(
                amountError = amountError,
                categoryError = categoryError,
                dateError = dateError,
            )
            return
        }
        val userId = currentUserId
        val category = state.category
        if (userId == null || category == null) return

        viewModelScope.launch {
            _uiState.value = AddIncomeUiState.Loading
            val amount = state.amount.replace(",", ".").toDouble()
            val result = if (state.isEditMode && editingTransactionId != null) {
                updateTransactionUseCase(
                    Transaction(
                        id = editingTransactionId,
                        userId = userId,
                        type = TransactionType.INCOME,
                        amount = amount,
                        category = category.name,
                        description = state.description.takeIf { it.isNotBlank() },
                        date = state.date,
                        createdAt = editingCreatedAt ?: Instant.now(),
                    ),
                )
            } else {
                createIncomeUseCase(
                    userId = userId,
                    amount = amount,
                    category = category.name,
                    date = state.date,
                    description = state.description,
                )
            }
            _uiState.value = when (result) {
                is Result.Success -> AddIncomeUiState.Saved
                is Result.Error -> AddIncomeUiState.Error(result.error.toMessage())
            }
        }
    }
}
