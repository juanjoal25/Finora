package com.finora.app.domain.usecase.auth

import com.finora.app.core.result.AppError
import com.finora.app.core.result.Result
import com.finora.app.core.validation.Validator
import com.finora.app.domain.model.User
import com.finora.app.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        Validator.validateEmail(email).errorMessageOrNull?.let {
            return Result.Error(AppError.Validation(it))
        }
        Validator.validatePassword(password).errorMessageOrNull?.let {
            return Result.Error(AppError.Validation(it))
        }
        return authRepository.login(email.trim().lowercase(), password)
    }
}
