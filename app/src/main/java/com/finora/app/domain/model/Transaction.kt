package com.finora.app.domain.model

import java.time.Instant
import java.time.LocalDate

enum class TransactionType { INCOME, EXPENSE }

data class Transaction(
    val id: String,
    val userId: String,
    val type: TransactionType,
    val amount: Double,
    val category: String,
    val description: String?,
    val date: LocalDate,
    val createdAt: Instant,
)
