package com.finora.app.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finora.app.core.result.Result
import com.finora.app.core.result.toMessage
import com.finora.app.core.validation.Validator
import com.finora.app.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _formState = MutableStateFlow(LoginFormState())
    val formState: StateFlow<LoginFormState> = _formState.asStateFlow()

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChange(value: String) {
        _formState.value = _formState.value.copy(email = value, emailError = null)
    }

    fun onPasswordChange(value: String) {
        _formState.value = _formState.value.copy(password = value, passwordError = null)
    }

    fun onTogglePasswordVisibility() {
        _formState.value = _formState.value.copy(isPasswordVisible = !_formState.value.isPasswordVisible)
    }

    fun onLoginClick() {
        val state = _formState.value
        val emailError = Validator.validateEmail(state.email).errorMessageOrNull
        val passwordError = Validator.validatePassword(state.password).errorMessageOrNull
        if (emailError != null || passwordError != null) {
            _formState.value = state.copy(emailError = emailError, passwordError = passwordError)
            return
        }
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            when (val result = loginUseCase(state.email, state.password)) {
                is Result.Success -> _uiState.value = LoginUiState.Success
                is Result.Error -> _uiState.value = LoginUiState.Error(result.error.toMessage())
            }
        }
    }
}
