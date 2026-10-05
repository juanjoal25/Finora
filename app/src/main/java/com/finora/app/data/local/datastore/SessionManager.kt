package com.finora.app.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.finora.app.di.SessionDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Persists only the current user's id (not the full profile) so session state stays tiny
 * and the rest of the user is always re-read fresh from Room.
 */
class SessionManager @Inject constructor(
    @SessionDataStore private val dataStore: DataStore<Preferences>,
) {
    private val currentUserIdKey = stringPreferencesKey("current_user_id")

    val currentUserIdFlow: Flow<String?> = dataStore.data.map { it[currentUserIdKey] }

    suspend fun setCurrentUserId(userId: String) {
        dataStore.edit { it[currentUserIdKey] = userId }
    }

    suspend fun clearSession() {
        dataStore.edit { it.remove(currentUserIdKey) }
    }
}
