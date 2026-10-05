package com.finora.app.presentation.transaction.expense

import com.finora.app.domain.model.Category
import java.time.LocalDate

sealed interface AddExpenseUiState {
    data object Idle : AddExpenseUiState
    data object Loading : AddExpenseUiState
    data object Saved : AddExpenseUiState
    data class Error(val message: String) : AddExpenseUiState
}

data class AddExpenseFormState(
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
