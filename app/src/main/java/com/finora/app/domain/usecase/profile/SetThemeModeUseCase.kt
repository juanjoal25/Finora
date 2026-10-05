package com.finora.app.domain.usecase.profile

import com.finora.app.domain.model.ThemeMode
import com.finora.app.domain.repository.SettingsRepository
import javax.inject.Inject

class SetThemeModeUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    suspend operator fun invoke(mode: ThemeMode) = settingsRepository.setThemeMode(mode)
}
