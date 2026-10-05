package com.finora.app.data.repository

import com.finora.app.core.result.AppError
import com.finora.app.core.result.Result
import com.finora.app.data.local.dao.TransactionDao
import com.finora.app.data.mapper.toDomain
import com.finora.app.data.mapper.toEntity
import com.finora.app.di.IoDispatcher
import com.finora.app.domain.model.Transaction
import com.finora.app.domain.model.TransactionType
import com.finora.app.domain.repository.TransactionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : TransactionRepository {

    override fun observeTransactions(userId: String): Flow<List<Transaction>> =
        transactionDao.observeByUser(userId).map { list -> list.map { it.toDomain() } }

    override fun observeTransactionsByType(userId: String, type: TransactionType): Flow<List<Transaction>> =
        transactionDao.observeByUserAndType(userId, type).map { list -> list.map { it.toDomain() } }

    override suspend fun getById(id: String): Transaction? = withContext(ioDispatcher) {
        transactionDao.getById(id)?.toDomain()
    }

    override suspend fun insert(transaction: Transaction): Result<Unit> = withContext(ioDispatcher) {
        runCatching { transactionDao.insert(transaction.toEntity()) }
            .fold(
                onSuccess = { Result.Success(Unit) },
                onFailure = { Result.Error(AppError.Database(it.message ?: "No se pudo guardar el movimiento")) },
            )
    }

    override suspend fun update(transaction: Transaction): Result<Unit> = withContext(ioDispatcher) {
        runCatching { transactionDao.update(transaction.toEntity()) }
            .fold(
                onSuccess = { Result.Success(Unit) },
                onFailure = { Result.Error(AppError.Database(it.message ?: "No se pudo actualizar el movimiento")) },
            )
    }

    override suspend fun delete(id: String): Result<Unit> = withContext(ioDispatcher) {
        runCatching { transactionDao.delete(id) }
            .fold(
                onSuccess = { Result.Success(Unit) },
                onFailure = { Result.Error(AppError.Database(it.message ?: "No se pudo eliminar el movimiento")) },
            )
    }
}
