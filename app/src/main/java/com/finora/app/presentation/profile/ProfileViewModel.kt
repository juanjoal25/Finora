package com.finora.app.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finora.app.domain.usecase.auth.GetCurrentUserUseCase
import com.finora.app.domain.usecase.auth.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {
            val user = getCurrentUserUseCase()
            _uiState.value = if (user != null) {
                ProfileUiState.Success(user)
            } else {
                ProfileUiState.Error("No se pudo cargar tu perfil")
            }
        }
    }

    fun onLogoutConfirmed() {
        viewModelScope.launch { logoutUseCase() }
    }
}
