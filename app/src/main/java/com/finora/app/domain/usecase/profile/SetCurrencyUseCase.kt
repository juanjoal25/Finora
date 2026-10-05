package com.finora.app.domain.usecase.profile

import com.finora.app.domain.model.Currency
import com.finora.app.domain.repository.SettingsRepository
import javax.inject.Inject

class SetCurrencyUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    suspend operator fun invoke(currency: Currency) = settingsRepository.setCurrency(currency)
}
