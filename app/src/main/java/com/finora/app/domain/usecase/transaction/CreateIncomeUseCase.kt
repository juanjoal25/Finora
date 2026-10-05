package com.finora.app.domain.usecase.transaction

import com.finora.app.core.result.AppError
import com.finora.app.core.result.Result
import com.finora.app.core.validation.Validator
import com.finora.app.domain.model.Transaction
import com.finora.app.domain.model.TransactionType
import com.finora.app.domain.repository.TransactionRepository
import java.time.Instant
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject

class CreateIncomeUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) {
    suspend operator fun invoke(
        userId: String,
        amount: Double,
        category: String,
        date: LocalDate,
        description: String?,
    ): Result<Unit> {
        validate(amount, category, date)?.let { return it }
        val transaction = Transaction(
            id = UUID.randomUUID().toString(),
            userId = userId,
            type = TransactionType.INCOME,
            amount = amount,
            category = category,
            description = description?.takeIf { it.isNotBlank() },
            date = date,
            createdAt = Instant.now(),
        )
        return transactionRepository.insert(transaction)
    }

    private fun validate(amount: Double, category: String, date: LocalDate?): Result.Error? {
        Validator.validateAmount(amount.toString()).errorMessageOrNull?.let {
            return Result.Error(AppError.Validation(it))
        }
        Validator.validateCategory(category).errorMessageOrNull?.let {
            return Result.Error(AppError.Validation(it))
        }
        Validator.validateDate(date).errorMessageOrNull?.let {
            return Result.Error(AppError.Validation(it))
        }
        return null
    }
}
