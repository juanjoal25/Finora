package com.finora.app.presentation.profile

import com.finora.app.domain.model.User

sealed interface ProfileUiState {
    data object Loading : ProfileUiState
    data class Success(val user: User) : ProfileUiState
    data class Error(val message: String) : ProfileUiState
}
