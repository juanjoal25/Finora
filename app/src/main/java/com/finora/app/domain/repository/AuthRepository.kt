package com.finora.app.domain.repository

import com.finora.app.core.result.Result
import com.finora.app.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Pure contract for authentication. The v1 implementation simulates auth locally with
 * Room + DataStore ([com.finora.app.data.repository.AuthRepositoryImpl]); swapping to a
 * real backend (Firebase, Supabase, custom REST) later only means writing a new
 * implementation of this interface and re-pointing the `@Binds` in
 * [com.finora.app.di.RepositoryModule] — no changes to domain or presentation.
 */
interface AuthRepository {
    suspend fun register(name: String, email: String, password: String): Result<User>
    suspend fun login(email: String, password: String): Result<User>
    suspend fun logout()
    suspend fun getCurrentUser(): User?
    fun observeCurrentUserId(): Flow<String?>
}
