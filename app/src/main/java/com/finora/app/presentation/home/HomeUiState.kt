package com.finora.app.presentation.home

import com.finora.app.domain.model.Transaction

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class Success(
        val userName: String,
        val balance: Double,
        val income: Double,
        val expenses: Double,
        val recentTransactions: List<Transaction>,
        val isBalanceVisible: Boolean,
    ) : HomeUiState

    data class Empty(val userName: String) : HomeUiState

    data class Error(val message: String) : HomeUiState
}
