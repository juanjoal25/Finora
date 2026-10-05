package com.finora.app.domain.repository

import com.finora.app.domain.model.CreditsInfo
import com.finora.app.domain.model.Currency
import com.finora.app.domain.model.ThemeMode
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun observeThemeMode(): Flow<ThemeMode>
    suspend fun setThemeMode(mode: ThemeMode)

    fun observeCurrency(): Flow<Currency>
    suspend fun setCurrency(currency: Currency)

    fun observeCredits(): Flow<CreditsInfo>
    suspend fun setCredits(info: CreditsInfo)
}
