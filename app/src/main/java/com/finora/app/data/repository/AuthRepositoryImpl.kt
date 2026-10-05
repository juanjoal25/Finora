package com.finora.app.data.repository

import com.finora.app.core.result.AppError
import com.finora.app.core.result.Result
import com.finora.app.core.utils.PasswordHasher
import com.finora.app.data.local.dao.UserDao
import com.finora.app.data.local.datastore.SessionManager
import com.finora.app.data.local.entities.UserEntity
import com.finora.app.data.mapper.toDomain
import com.finora.app.di.IoDispatcher
import com.finora.app.domain.model.User
import com.finora.app.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.time.Instant
import java.util.UUID
import javax.inject.Inject

/**
 * v1 local, simulated auth implementation (Room + DataStore, no backend). See
 * [AuthRepository] for how this is designed to be swapped for a real backend later.
 */
class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val sessionManager: SessionManager,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : AuthRepository {

    override suspend fun register(name: String, email: String, password: String): Result<User> =
        withContext(ioDispatcher) {
            if (userDao.findByEmail(email) != null) {
                return@withContext Result.Error(AppError.Auth.EmailAlreadyRegistered)
            }
            val hashed = PasswordHasher.hash(password)
            val entity = UserEntity(
                id = UUID.randomUUID().toString(),
                name = name,
                email = email,
                passwordHash = hashed.hash,
                passwordSalt = hashed.salt,
                createdAt = Instant.now(),
            )
            userDao.insert(entity)
            Result.Success(entity.toDomain())
        }

    override suspend fun login(email: String, password: String): Result<User> = withContext(ioDispatcher) {
        val entity = userDao.findByEmail(email)
            ?: return@withContext Result.Error(AppError.Auth.InvalidCredentials)
        val isValid = PasswordHasher.verify(password, entity.passwordHash, entity.passwordSalt)
        if (!isValid) {
            return@withContext Result.Error(AppError.Auth.InvalidCredentials)
        }
        sessionManager.setCurrentUserId(entity.id)
        Result.Success(entity.toDomain())
    }

    override suspend fun logout() {
        sessionManager.clearSession()
    }

    override suspend fun getCurrentUser(): User? = withContext(ioDispatcher) {
        val userId = sessionManager.currentUserIdFlow.first() ?: return@withContext null
        userDao.findById(userId)?.toDomain()
    }

    override fun observeCurrentUserId(): Flow<String?> = sessionManager.currentUserIdFlow
}
