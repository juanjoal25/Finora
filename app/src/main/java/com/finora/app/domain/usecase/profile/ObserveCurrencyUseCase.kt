package com.finora.app.domain.usecase.profile

import com.finora.app.domain.model.Currency
import com.finora.app.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveCurrencyUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
) {
    operator fun invoke(): Flow<Currency> = settingsRepository.observeCurrency()
}
