package com.finora.app.domain.usecase.transaction

import com.finora.app.domain.model.Transaction
import com.finora.app.domain.model.TransactionType
import com.finora.app.testutil.FakeTransactionRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.Instant
import java.time.LocalDate

class GetTransactionsUseCaseTest {

    private lateinit var transactionRepository: FakeTransactionRepository
    private lateinit var getTransactionsUseCase: GetTransactionsUseCase

    @Before
    fun setUp() {
        transactionRepository = FakeTransactionRepository()
        getTransactionsUseCase = GetTransactionsUseCase(transactionRepository)
    }

    private fun transaction(
        id: String,
        type: TransactionType,
        amount: Double,
        date: LocalDate = LocalDate.now(),
    ) = Transaction(
        id = id,
        userId = "user-1",
        type = type,
        amount = amount,
        category = "Otros",
        description = null,
        date = date,
        createdAt = Instant.now(),
    )

    @Test
    fun `orders transactions by date descending`() = runTest {
        transactionRepository.insert(transaction("1", TransactionType.INCOME, 1000.0, LocalDate.now().minusDays(2)))
        transactionRepository.insert(transaction("2", TransactionType.INCOME, 2000.0, LocalDate.now()))

        val result = getTransactionsUseCase("user-1").first()

        assertEquals("2", result.first().id)
    }

    @Test
    fun `balance calculator sums income minus expenses`() {
        val transactions = listOf(
            transaction("1", TransactionType.INCOME, 3_000_000.0),
            transaction("2", TransactionType.EXPENSE, 850_000.0),
        )

        val summary = BalanceCalculator.summarize(transactions)

        assertEquals(2_150_000.0, summary.balance, 0.0)
        assertEquals(3_000_000.0, summary.income, 0.0)
        assertEquals(850_000.0, summary.expenses, 0.0)
    }
}
