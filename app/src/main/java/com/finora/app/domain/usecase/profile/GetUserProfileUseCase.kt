package com.finora.app.domain.usecase.profile

import com.finora.app.domain.model.User
import com.finora.app.domain.repository.UserRepository
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(userId: String): User? = userRepository.getUser(userId)
}
