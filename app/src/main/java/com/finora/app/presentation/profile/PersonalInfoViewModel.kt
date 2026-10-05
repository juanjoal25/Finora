package com.finora.app.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finora.app.core.result.Result
import com.finora.app.core.result.toMessage
import com.finora.app.core.validation.Validator
import com.finora.app.domain.model.User
import com.finora.app.domain.usecase.auth.GetCurrentUserUseCase
import com.finora.app.domain.usecase.profile.UpdateUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface PersonalInfoUiState {
    data object Loading : PersonalInfoUiState
    data object Idle : PersonalInfoUiState
    data object Saving : PersonalInfoUiState
    data object Saved : PersonalInfoUiState
    data class Error(val message: String) : PersonalInfoUiState
}

data class PersonalInfoFormState(
    val name: String = "",
    val email: String = "",
    val nameError: String? = null,
    val emailError: String? = null,
)

@HiltViewModel
class PersonalInfoViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val updateUserProfileUseCase: UpdateUserProfileUseCase,
) : ViewModel() {

    private val _formState = MutableStateFlow(PersonalInfoFormState())
    val formState: StateFlow<PersonalInfoFormState> = _formState.asStateFlow()

    private val _uiState = MutableStateFlow<PersonalInfoUiState>(PersonalInfoUiState.Loading)
    val uiState: StateFlow<PersonalInfoUiState> = _uiState.asStateFlow()

    private var currentUser: User? = null

    init {
        viewModelScope.launch {
            val user = getCurrentUserUseCase()
            if (user == null) {
                _uiState.value = PersonalInfoUiState.Error("No se pudo cargar tu perfil")
                return@launch
            }
            currentUser = user
            _formState.value = PersonalInfoFormState(name = user.name, email = user.email)
            _uiState.value = PersonalInfoUiState.Idle
        }
    }

    fun onNameChange(value: String) {
        _formState.value = _formState.value.copy(name = value, nameError = null)
    }

    fun onEmailChange(value: String) {
        _formState.value = _formState.value.copy(email = value, emailError = null)
    }

    fun onSaveClick() {
        val state = _formState.value
        val nameError = Validator.validateFullName(state.name).errorMessageOrNull
        val emailError = Validator.validateEmail(state.email).errorMessageOrNull
        if (nameError != null || emailError != null) {
            _formState.value = state.copy(nameError = nameError, emailError = emailError)
            return
        }
        val user = currentUser ?: return
        viewModelScope.launch {
            _uiState.value = PersonalInfoUiState.Saving
            val updated = user.copy(name = state.name.trim(), email = state.email.trim().lowercase())
            val result = updateUserProfileUseCase(updated)
            _uiState.value = when (result) {
                is Result.Success -> PersonalInfoUiState.Saved
                is Result.Error -> PersonalInfoUiState.Error(result.error.toMessage())
            }
        }
    }
}
