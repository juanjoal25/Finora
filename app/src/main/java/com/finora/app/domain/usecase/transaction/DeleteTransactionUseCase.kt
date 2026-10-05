package com.finora.app.domain.usecase.transaction

import com.finora.app.core.result.Result
import com.finora.app.domain.repository.TransactionRepository
import javax.inject.Inject

class DeleteTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) {
    suspend operator fun invoke(id: String): Result<Unit> = transactionRepository.delete(id)
}
