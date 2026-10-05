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

class CreateIncomeUseCaseTest {

    private lateinit var transactionRepository: FakeTransactionRepository
    private lateinit var createIncomeUseCase: CreateIncomeUseCase

    @Before
    fun setUp() {
        transactionRepository = FakeTransactionRepository()
        createIncomeUseCase = CreateIncomeUseCase(transactionRepository)
    }

    @Test
    fun `zero amount fails validation`() = runTest {
        val result = createIncomeUseCase("user-1", 0.0, "Salario", LocalDate.now(), null)
        assertTrue(result is Result.Error)
    }

    @Test
    fun `blank category fails validation`() = runTest {
        val result = createIncomeUseCase("user-1", 1000.0, "", LocalDate.now(), null)
        assertTrue(result is Result.Error)
    }

    @Test
    fun `valid income is created`() = runTest {
        val result = createIncomeUseCase("user-1", 1_000_000.0, "Salario", LocalDate.now(), "Pago mensual")

        assertTrue(result is Result.Success)
        val transactions = transactionRepository.observeTransactions("user-1").first()
        assertEquals(1, transactions.size)
        assertEquals(TransactionType.INCOME, transactions.first().type)
        assertEquals(1_000_000.0, transactions.first().amount, 0.0)
    }
}
