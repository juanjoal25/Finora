package com.finora.app.testutil

import com.finora.app.core.result.Result
import com.finora.app.domain.model.Transaction
import com.finora.app.domain.model.TransactionType
import com.finora.app.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class FakeTransactionRepository : TransactionRepository {

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())

    override fun observeTransactions(userId: String): Flow<List<Transaction>> =
        _transactions.map { list -> list.filter { it.userId == userId } }

    override fun observeTransactionsByType(userId: String, type: TransactionType): Flow<List<Transaction>> =
        _transactions.map { list -> list.filter { it.userId == userId && it.type == type } }

    override suspend fun getById(id: String): Transaction? = _transactions.value.firstOrNull { it.id == id }

    override suspend fun insert(transaction: Transaction): Result<Unit> {
        _transactions.value = _transactions.value + transaction
        return Result.Success(Unit)
    }

    override suspend fun update(transaction: Transaction): Result<Unit> {
        _transactions.value = _transactions.value.map { if (it.id == transaction.id) transaction else it }
        return Result.Success(Unit)
    }

    override suspend fun delete(id: String): Result<Unit> {
        _transactions.value = _transactions.value.filterNot { it.id == id }
        return Result.Success(Unit)
    }
}
