package com.finora.app.testutil

import com.finora.app.core.result.AppError
import com.finora.app.core.result.Result
import com.finora.app.domain.model.User
import com.finora.app.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.Instant
import java.util.UUID

class FakeAuthRepository : AuthRepository {

    private data class StoredUser(val user: User, val password: String)

    private val usersByEmail = mutableMapOf<String, StoredUser>()
    private val _currentUserId = MutableStateFlow<String?>(null)

    override suspend fun register(name: String, email: String, password: String): Result<User> {
        if (usersByEmail.containsKey(email)) {
            return Result.Error(AppError.Auth.EmailAlreadyRegistered)
        }
        val user = User(id = UUID.randomUUID().toString(), name = name, email = email, createdAt = Instant.now())
        usersByEmail[email] = StoredUser(user, password)
        return Result.Success(user)
    }

    override suspend fun login(email: String, password: String): Result<User> {
        val stored = usersByEmail[email] ?: return Result.Error(AppError.Auth.InvalidCredentials)
        if (stored.password != password) return Result.Error(AppError.Auth.InvalidCredentials)
        _currentUserId.value = stored.user.id
        return Result.Success(stored.user)
    }

    override suspend fun logout() {
        _currentUserId.value = null
    }

    override suspend fun getCurrentUser(): User? {
        val id = _currentUserId.value ?: return null
        return usersByEmail.values.firstOrNull { it.user.id == id }?.user
    }

    override fun observeCurrentUserId(): Flow<String?> = _currentUserId
}
