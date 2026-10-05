package com.finora.app.core.validation

import java.time.LocalDate

/**
 * Single source of truth for form validation, shared by Register and Add Income/Expense
 * so rules and messages never drift between screens.
 */
object Validator {

    private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    const val MIN_PASSWORD_LENGTH = 6

    fun validateFullName(name: String): ValidationResult =
        if (name.isBlank()) ValidationResult.Invalid("El nombre es obligatorio")
        else ValidationResult.Valid

    fun validateEmail(email: String): ValidationResult = when {
        email.isBlank() -> ValidationResult.Invalid("El correo es obligatorio")
        !EMAIL_REGEX.matches(email.trim()) -> ValidationResult.Invalid("Ingresa un correo válido")
        else -> ValidationResult.Valid
    }

    fun validatePassword(password: String): ValidationResult = when {
        password.isBlank() -> ValidationResult.Invalid("La contraseña es obligatoria")
        password.length < MIN_PASSWORD_LENGTH ->
            ValidationResult.Invalid("La contraseña debe tener al menos $MIN_PASSWORD_LENGTH caracteres")
        else -> ValidationResult.Valid
    }

    fun validatePasswordConfirmation(password: String, confirmation: String): ValidationResult = when {
        confirmation.isBlank() -> ValidationResult.Invalid("Confirma tu contraseña")
        confirmation != password -> ValidationResult.Invalid("Las contraseñas no coinciden")
        else -> ValidationResult.Valid
    }

    fun validateAmount(rawAmount: String): ValidationResult {
        val normalized = rawAmount.trim().replace(",", ".")
        val amount = normalized.toDoubleOrNull()
        return when {
            rawAmount.isBlank() -> ValidationResult.Invalid("El monto es obligatorio")
            amount == null -> ValidationResult.Invalid("Ingresa un monto numérico válido")
            amount <= 0.0 -> ValidationResult.Invalid("El monto debe ser mayor a cero")
            else -> ValidationResult.Valid
        }
    }

    fun validateCategory(categoryId: String?): ValidationResult =
        if (categoryId.isNullOrBlank()) ValidationResult.Invalid("Selecciona una categoría")
        else ValidationResult.Valid

    fun validateDate(date: LocalDate?): ValidationResult =
        if (date == null) ValidationResult.Invalid("Selecciona una fecha válida")
        else ValidationResult.Valid
}
