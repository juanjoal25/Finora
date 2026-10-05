package com.finora.app.data.repository

import com.finora.app.data.local.datastore.ThemePreferencesDataStore
import com.finora.app.domain.model.CreditsInfo
import com.finora.app.domain.model.Currency
import com.finora.app.domain.model.ThemeMode
import com.finora.app.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val themePreferencesDataStore: ThemePreferencesDataStore,
) : SettingsRepository {
    override fun observeThemeMode(): Flow<ThemeMode> = themePreferencesDataStore.themeModeFlow
    override suspend fun setThemeMode(mode: ThemeMode) = themePreferencesDataStore.setThemeMode(mode)

    override fun observeCurrency(): Flow<Currency> = themePreferencesDataStore.currencyFlow
    override suspend fun setCurrency(currency: Currency) = themePreferencesDataStore.setCurrency(currency)

    override fun observeCredits(): Flow<CreditsInfo> = themePreferencesDataStore.creditsFlow
    override suspend fun setCredits(info: CreditsInfo) = themePreferencesDataStore.setCredits(info)
}
