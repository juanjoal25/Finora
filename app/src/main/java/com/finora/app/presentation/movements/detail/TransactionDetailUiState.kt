package com.finora.app.presentation.movements.detail

import com.finora.app.domain.model.Transaction

sealed interface TransactionDetailUiState {
    data object Loading : TransactionDetailUiState
    data class Success(val transaction: Transaction) : TransactionDetailUiState
    data class Error(val message: String) : TransactionDetailUiState
}
