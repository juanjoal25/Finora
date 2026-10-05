package com.finora.app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.finora.app.domain.model.TransactionType
import java.time.Instant
import java.time.LocalDate

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val type: TransactionType,
    val amount: Double,
    val category: String,
    val description: String?,
    val date: LocalDate,
    val createdAt: Instant,
)
