package com.finora.app.domain.usecase.transaction

import com.finora.app.domain.model.Transaction
import com.finora.app.domain.model.TransactionType
import com.finora.app.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
) {
    operator fun invoke(userId: String, type: TransactionType? = null): Flow<List<Transaction>> {
        val source = if (type == null) {
            transactionRepository.observeTransactions(userId)
        } else {
            transactionRepository.observeTransactionsByType(userId, type)
        }
        return source.map { list ->
            list.sortedWith(compareByDescending<Transaction> { it.date }.thenByDescending { it.createdAt })
        }
    }
}
