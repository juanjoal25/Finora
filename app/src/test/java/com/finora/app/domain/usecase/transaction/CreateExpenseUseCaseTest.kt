package com.finora.app.domain.usecase.transaction

import com.finora.app.core.result.Result
import com.finora.app.domain.model.TransactionType
import com.finora.app.testutil.FakeTransactionRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class CreateExpenseUseCaseTest {

    private lateinit var transactionRepository: FakeTransactionRepository
    private lateinit var createExpenseUseCase: CreateExpenseUseCase

    @Before
    fun setUp() {
        transactionRepository = FakeTransactionRepository()
        createExpenseUseCase = CreateExpenseUseCase(transactionRepository)
    }

    @Test
    fun `negative amount fails validation`() = runTest {
        val result = createExpenseUseCase("user-1", -100.0, "Transporte", LocalDate.now(), null)
        assertTrue(result is Result.Error)
    }

    @Test
    fun `blank category fails validation`() = runTest {
        val result = createExpenseUseCase("user-1", 35_000.0, "", LocalDate.now(), null)
        assertTrue(result is Result.Error)
    }

    @Test
    fun `valid expense is created`() = runTest {
        val result = createExpenseUseCase("user-1", 35_000.0, "Alimentación", LocalDate.now(), "Almuerzo")

        assertTrue(result is Result.Success)
        val transactions = transactionRepository.observeTransactions("user-1").first()
        assertEquals(1, transactions.size)
        assertEquals(TransactionType.EXPENSE, transactions.first().type)
        assertEquals(35_000.0, transactions.first().amount, 0.0)
    }
}
