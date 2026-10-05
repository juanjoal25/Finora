package com.finora.app.domain.usecase.profile

import com.finora.app.core.result.AppError
import com.finora.app.core.result.Result
import com.finora.app.core.validation.Validator
import com.finora.app.domain.model.User
import com.finora.app.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUserProfileUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(user: User): Result<User> {
        Validator.validateFullName(user.name).errorMessageOrNull?.let {
            return Result.Error(AppError.Validation(it))
        }
        Validator.validateEmail(user.email).errorMessageOrNull?.let {
            return Result.Error(AppError.Validation(it))
        }
        return userRepository.updateProfile(user)
    }
}
