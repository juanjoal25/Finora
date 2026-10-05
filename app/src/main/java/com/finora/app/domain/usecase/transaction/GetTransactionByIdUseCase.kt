package com.finora.app.domain.usecase.transaction

import com.finora.app.domain.model.Transaction
import com.finora.app.domain.repository.TransactionRepository
import javax.inject.Inject

class GetTransactionByIdUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) {
    suspend operator fun invoke(id: String): Transaction? = transactionRepository.getById(id)
}
