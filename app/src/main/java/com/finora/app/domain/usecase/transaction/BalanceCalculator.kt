package com.finora.app.domain.usecase.transaction

import com.finora.app.domain.model.Transaction
import com.finora.app.domain.model.TransactionType

/**
 * Pure domain helper (not a UseCase, no state/DI needed) shared by Home and Movements to
 * compute balance/income/expense totals from a list of transactions.
 */
object BalanceCalculator {

    data class Summary(val balance: Double, val income: Double, val expenses: Double)

    fun summarize(transactions: List<Transaction>): Summary {
        val income = transactions.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val expenses = transactions.filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }
        return Summary(balance = income - expenses, income = income, expenses = expenses)
    }
}
