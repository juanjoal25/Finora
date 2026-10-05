package com.finora.app.domain.usecase.transaction

import com.finora.app.core.result.Result
import com.finora.app.domain.model.Transaction
import com.finora.app.domain.model.TransactionType
import com.finora.app.testutil.FakeTransactionRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.Instant
import java.time.LocalDate

class DeleteTransactionUseCaseTest {

    private lateinit var transactionRepository: FakeTransactionRepository
    private lateinit var deleteTransactionUseCase: DeleteTransactionUseCase

    @Before
    fun setUp() {
        transactionRepository = FakeTransactionRepository()
        deleteTransactionUseCase = DeleteTransactionUseCase(transactionRepository)
    }

    @Test
    fun `deleting an existing transaction removes it`() = runTest {
        val transaction = Transaction(
            id = "1",
            userId = "user-1",
            type = TransactionType.EXPENSE,
            amount = 10_000.0,
            category = "Otros",
            description = null,
            date = LocalDate.now(),
            createdAt = Instant.now(),
        )
        transactionRepository.insert(transaction)

        val result = deleteTransactionUseCase("1")

        assertTrue(result is Result.Success)
        assertNull(transactionRepository.getById("1"))
    }
}
