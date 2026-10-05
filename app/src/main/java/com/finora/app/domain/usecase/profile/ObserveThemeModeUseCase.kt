package com.finora.app.domain.usecase.profile

import com.finora.app.domain.model.ThemeMode
import com.finora.app.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveThemeModeUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    operator fun invoke(): Flow<ThemeMode> = settingsRepository.observeThemeMode()
}
