package com.finora.app.domain.usecase.transaction

import com.finora.app.core.result.AppError
import com.finora.app.core.result.Result
import com.finora.app.core.validation.Validator
import com.finora.app.domain.model.Transaction
import com.finora.app.domain.repository.TransactionRepository
import javax.inject.Inject

class UpdateTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) {
    suspend operator fun invoke(transaction: Transaction): Result<Unit> {
        Validator.validateAmount(transaction.amount.toString()).errorMessageOrNull?.let {
            return Result.Error(AppError.Validation(it))
        }
        Validator.validateCategory(transaction.category).errorMessageOrNull?.let {
            return Result.Error(AppError.Validation(it))
        }
        Validator.validateDate(transaction.date).errorMessageOrNull?.let {
            return Result.Error(AppError.Validation(it))
        }
        return transactionRepository.update(transaction)
    }
}
