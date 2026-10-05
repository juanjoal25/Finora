package com.finora.app.domain.repository

import com.finora.app.core.result.Result
import com.finora.app.domain.model.User

interface UserRepository {
    suspend fun getUser(id: String): User?
    suspend fun updateProfile(user: User): Result<User>
}
