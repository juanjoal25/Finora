package com.finora.app.presentation.transaction.income

import com.finora.app.domain.model.Category
import java.time.LocalDate

sealed interface AddIncomeUiState {
    data object Idle : AddIncomeUiState
    data object Loading : AddIncomeUiState
    data object Saved : AddIncomeUiState
    data class Error(val message: String) : AddIncomeUiState
}

data class AddIncomeFormState(
    val isEditMode: Boolean = false,
    val amount: String = "",
    val category: Category? = null,
    val date: LocalDate = LocalDate.now(),
    val description: String = "",
    val categories: List<Category> = emptyList(),
    val amountError: String? = null,
    val categoryError: String? = null,
    val dateError: String? = null,
)
