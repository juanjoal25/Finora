package com.finora.app.presentation.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finora.app.domain.model.Currency
import com.finora.app.domain.model.ThemeMode
import com.finora.app.domain.usecase.auth.GetCurrentUserUseCase
import com.finora.app.domain.usecase.profile.ObserveCurrencyUseCase
import com.finora.app.domain.usecase.profile.ObserveThemeModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed interface SessionState {
    data object Loading : SessionState
    data class LoggedIn(val userId: String) : SessionState
    data object LoggedOut : SessionState
}

/**
 * Root-level state (session + theme) collected once at [com.finora.app.MainActivity] so the
 * whole app reacts immediately to login/logout and theme changes, instead of only the
 * screen that happens to be visible.
 */
@HiltViewModel
class RootViewModel @Inject constructor(
    getCurrentUserUseCase: GetCurrentUserUseCase,
    observeThemeModeUseCase: ObserveThemeModeUseCase,
    observeCurrencyUseCase: ObserveCurrencyUseCase,
) : ViewModel() {

    val sessionState: StateFlow<SessionState> = getCurrentUserUseCase.observeSession()
        .map { userId -> if (userId != null) SessionState.LoggedIn(userId) else SessionState.LoggedOut }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), SessionState.Loading)

    val themeMode: StateFlow<ThemeMode> = observeThemeModeUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), ThemeMode.SYSTEM)

    val currency: StateFlow<Currency> = observeCurrencyUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), Currency.COP)
}
