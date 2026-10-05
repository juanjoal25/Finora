package com.finora.app.presentation.auth.register

sealed interface RegisterUiState {
    data object Idle : RegisterUiState
    data object Loading : RegisterUiState
    data object Success : RegisterUiState
    data class Error(val message: String) : RegisterUiState
}

data class RegisterFormState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordVisible: Boolean = false,
    val nameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
)
