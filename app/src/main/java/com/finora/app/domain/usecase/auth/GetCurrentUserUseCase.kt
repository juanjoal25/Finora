package com.finora.app.domain.usecase.auth

import com.finora.app.domain.model.User
import com.finora.app.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(): User? = authRepository.getCurrentUser()

    fun observeSession(): Flow<String?> = authRepository.observeCurrentUserId()
}
