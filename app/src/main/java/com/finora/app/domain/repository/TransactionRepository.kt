package com.finora.app.domain.repository

import com.finora.app.core.result.Result
import com.finora.app.domain.model.Transaction
import com.finora.app.domain.model.TransactionType
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun observeTransactions(userId: String): Flow<List<Transaction>>
    fun observeTransactionsByType(userId: String, type: TransactionType): Flow<List<Transaction>>
    suspend fun getById(id: String): Transaction?
    suspend fun insert(transaction: Transaction): Result<Unit>
    suspend fun update(transaction: Transaction): Result<Unit>
    suspend fun delete(id: String): Result<Unit>
}
