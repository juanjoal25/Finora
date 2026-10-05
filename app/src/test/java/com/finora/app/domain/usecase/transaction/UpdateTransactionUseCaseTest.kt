package com.finora.app.domain.usecase.transaction

import com.finora.app.core.result.Result
import com.finora.app.domain.model.Transaction
import com.finora.app.domain.model.TransactionType
import com.finora.app.testutil.FakeTransactionRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.Instant
import java.time.LocalDate

class UpdateTransactionUseCaseTest {

    private lateinit var transactionRepository: FakeTransactionRepository
    private lateinit var updateTransactionUseCase: UpdateTransactionUseCase

    @Before
    fun setUp() {
        transactionRepository = FakeTransactionRepository()
        updateTransactionUseCase = UpdateTransactionUseCase(transactionRepository)
    }

    private fun sampleTransaction(amount: Double = 10_000.0, category: String = "Otros") = Transaction(
        id = "1",
        userId = "user-1",
        type = TransactionType.EXPENSE,
        amount = amount,
        category = category,
        description = null,
        date = LocalDate.now(),
        createdAt = Instant.now(),
    )

    @Test
    fun `zero amount fails validation`() = runTest {
        val result = updateTransactionUseCase(sampleTransaction(amount = 0.0))
        assertTrue(result is Result.Error)
    }

    @Test
    fun `valid update persists new values`() = runTest {
        val original = sampleTransaction()
        transactionRepository.insert(original)

        val updated = original.copy(amount = 20_000.0, category = "Transporte")
        val result = updateTransactionUseCase(updated)

        assertTrue(result is Result.Success)
        val stored = transactionRepository.getById("1")
        assertEquals(20_000.0, stored?.amount)
        assertEquals("Transporte", stored?.category)
    }
}
