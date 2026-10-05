package com.finora.app.domain.usecase.profile

import com.finora.app.domain.model.CreditsInfo
import com.finora.app.domain.repository.SettingsRepository
import javax.inject.Inject

class SetCreditsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    suspend operator fun invoke(info: CreditsInfo) = settingsRepository.setCredits(info)
}
