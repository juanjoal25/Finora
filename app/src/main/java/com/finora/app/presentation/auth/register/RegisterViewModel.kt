package com.finora.app.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finora.app.core.result.Result
import com.finora.app.core.result.toMessage
import com.finora.app.core.validation.Validator
import com.finora.app.domain.usecase.auth.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
) : ViewModel() {

    private val _formState = MutableStateFlow(RegisterFormState())
    val formState: StateFlow<RegisterFormState> = _formState.asStateFlow()

    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun onNameChange(value: String) {
        _formState.value = _formState.value.copy(name = value, nameError = null)
    }

    fun onEmailChange(value: String) {
        _formState.value = _formState.value.copy(email = value, emailError = null)
    }

    fun onPasswordChange(value: String) {
        _formState.value = _formState.value.copy(password = value, passwordError = null)
    }

    fun onConfirmPasswordChange(value: String) {
        _formState.value = _formState.value.copy(confirmPassword = value, confirmPasswordError = null)
    }

    fun onTogglePasswordVisibility() {
        _formState.value = _formState.value.copy(isPasswordVisible = !_formState.value.isPasswordVisible)
    }

    fun onRegisterClick() {
        val state = _formState.value
        val nameError = Validator.validateFullName(state.name).errorMessageOrNull
        val emailError = Validator.validateEmail(state.email).errorMessageOrNull
        val passwordError = Validator.validatePassword(state.password).errorMessageOrNull
        val confirmError = Validator.validatePasswordConfirmation(state.password, state.confirmPassword)
            .errorMessageOrNull
        if (listOf(nameError, emailError, passwordError, confirmError).any { it != null }) {
            _formState.value = state.copy(
                nameError = nameError,
                emailError = emailError,
                passwordError = passwordError,
                confirmPasswordError = confirmError,
            )
            return
        }
        viewModelScope.launch {
            _uiState.value = RegisterUiState.Loading
            val result = registerUserUseCase(state.name, state.email, state.password, state.confirmPassword)
            _uiState.value = when (result) {
                is Result.Success -> RegisterUiState.Success
                is Result.Error -> RegisterUiState.Error(result.error.toMessage())
            }
        }
    }
}
