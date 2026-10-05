package com.finora.app.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.finora.app.di.ThemeDataStore
import com.finora.app.domain.model.CreditsInfo
import com.finora.app.domain.model.Currency
import com.finora.app.domain.model.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Backs every lightweight, app-wide preference (theme, currency, "about" credits) with a
 * single DataStore file — these are all lookup-by-key scalars with no relational shape, so
 * they don't warrant separate stores.
 */
class ThemePreferencesDataStore @Inject constructor(
    @ThemeDataStore private val dataStore: DataStore<Preferences>,
) {
    private val themeModeKey = stringPreferencesKey("theme_mode")
    private val currencyKey = stringPreferencesKey("currency_code")
    private val creditsNameKey = stringPreferencesKey("credits_name")
    private val creditsEmailKey = stringPreferencesKey("credits_email")

    val themeModeFlow: Flow<ThemeMode> = dataStore.data.map { prefs ->
        prefs[themeModeKey]?.let { runCatching { ThemeMode.valueOf(it) }.getOrNull() } ?: ThemeMode.SYSTEM
    }

    suspend fun setThemeMode(mode: ThemeMode) {
        dataStore.edit { it[themeModeKey] = mode.name }
    }

    val currencyFlow: Flow<Currency> = dataStore.data.map { prefs ->
        prefs[currencyKey]?.let { runCatching { Currency.valueOf(it) }.getOrNull() } ?: Currency.COP
    }

    suspend fun setCurrency(currency: Currency) {
        dataStore.edit { it[currencyKey] = currency.name }
    }

    val creditsFlow: Flow<CreditsInfo> = dataStore.data.map { prefs ->
        CreditsInfo(name = prefs[creditsNameKey].orEmpty(), email = prefs[creditsEmailKey].orEmpty())
    }

    suspend fun setCredits(info: CreditsInfo) {
        dataStore.edit {
            it[creditsNameKey] = info.name
            it[creditsEmailKey] = info.email
        }
    }
}
