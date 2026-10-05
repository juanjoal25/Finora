package com.finora.app.presentation.movements.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finora.app.core.result.Result
import com.finora.app.domain.usecase.transaction.DeleteTransactionUseCase
import com.finora.app.domain.usecase.transaction.GetTransactionByIdUseCase
import com.finora.app.presentation.navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getTransactionByIdUseCase: GetTransactionByIdUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase,
) : ViewModel() {

    private val transactionId: String = checkNotNull(savedStateHandle[Routes.TransactionDetail.ARG_TRANSACTION_ID])

    private val _uiState = MutableStateFlow<TransactionDetailUiState>(TransactionDetailUiState.Loading)
    val uiState: StateFlow<TransactionDetailUiState> = _uiState.asStateFlow()

    private val _deleted = MutableSharedFlow<Unit>()
    val deleted: SharedFlow<Unit> = _deleted

    init {
        loadTransaction()
    }

    private fun loadTransaction() {
        viewModelScope.launch {
            val transaction = getTransactionByIdUseCase(transactionId)
            _uiState.value = if (transaction != null) {
                TransactionDetailUiState.Success(transaction)
            } else {
                TransactionDetailUiState.Error("No se encontró el movimiento")
            }
        }
    }

    fun onDeleteConfirmed() {
        viewModelScope.launch {
            when (deleteTransactionUseCase(transactionId)) {
                is Result.Success -> _deleted.emit(Unit)
                is Result.Error ->
                    _uiState.value = TransactionDetailUiState.Error("No se pudo eliminar el movimiento")
            }
        }
    }
}
