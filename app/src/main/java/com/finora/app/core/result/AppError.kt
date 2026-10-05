package com.finora.app.core.result

/**
 * Typed error contract shared by every UseCase -> ViewModel boundary, so screens never
 * have to deal with raw exceptions.
 */
sealed class AppError {

    data class Validation(val message: String) : AppError()

    sealed class Auth : AppError() {
        data object InvalidCredentials : Auth()
        data object EmailAlreadyRegistered : Auth()
        data object NotAuthenticated : Auth()
    }

    data class NotFound(val message: String) : AppError()

    data class Database(val message: String) : AppError()

    data class Unknown(val message: String) : AppError()
}

/** Single place mapping every [AppError] to a user-facing (Spanish) message. */
fun AppError.toMessage(): String = when (this) {
    is AppError.Validation -> message
    AppError.Auth.InvalidCredentials -> "Correo o contraseña incorrectos"
    AppError.Auth.EmailAlreadyRegistered -> "Ya existe una cuenta con este correo"
    AppError.Auth.NotAuthenticated -> "Debes iniciar sesión nuevamente"
    is AppError.NotFound -> message
    is AppError.Database -> message
    is AppError.Unknown -> message
}
