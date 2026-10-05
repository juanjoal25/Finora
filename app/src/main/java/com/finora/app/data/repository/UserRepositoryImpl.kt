package com.finora.app.data.repository

import com.finora.app.core.result.AppError
import com.finora.app.core.result.Result
import com.finora.app.data.local.dao.UserDao
import com.finora.app.data.mapper.toDomain
import com.finora.app.di.IoDispatcher
import com.finora.app.domain.model.User
import com.finora.app.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : UserRepository {

    override suspend fun getUser(id: String): User? = withContext(ioDispatcher) {
        userDao.findById(id)?.toDomain()
    }

    override suspend fun updateProfile(user: User): Result<User> = withContext(ioDispatcher) {
        val existing = userDao.findById(user.id)
            ?: return@withContext Result.Error(AppError.NotFound("Usuario no encontrado"))
        if (existing.email != user.email && userDao.findByEmail(user.email) != null) {
            return@withContext Result.Error(AppError.Auth.EmailAlreadyRegistered)
        }
        val updated = existing.copy(name = user.name, email = user.email)
        userDao.update(updated)
        Result.Success(updated.toDomain())
    }
}
