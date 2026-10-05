package com.finora.app.presentation.movements.list

import com.finora.app.domain.model.Transaction

enum class MovementsFilter { ALL, INCOME, EXPENSE }

sealed interface MovementsUiState {
    data object Loading : MovementsUiState

    data class Success(
        val transactions: List<Transaction>,
        val filter: MovementsFilter,
        val query: String,
    ) : MovementsUiState

    /** No movements exist at all yet (distinct from a filter/search yielding zero results). */
    data object Empty : MovementsUiState

    data class Error(val message: String) : MovementsUiState
}
