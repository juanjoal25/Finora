package com.finora.app.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finora.app.domain.model.ThemeMode
import com.finora.app.domain.usecase.profile.ObserveThemeModeUseCase
import com.finora.app.domain.usecase.profile.SetThemeModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    observeThemeModeUseCase: ObserveThemeModeUseCase,
    private val setThemeModeUseCase: SetThemeModeUseCase,
) : ViewModel() {

    val themeMode: StateFlow<ThemeMode> = observeThemeModeUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), ThemeMode.SYSTEM)

    fun onThemeModeSelected(mode: ThemeMode) {
        viewModelScope.launch { setThemeModeUseCase(mode) }
    }
}
