package com.finora.app.data.mapper

import com.finora.app.data.local.entities.TransactionEntity
import com.finora.app.domain.model.Transaction

fun TransactionEntity.toDomain(): Transaction = Transaction(
    id = id,
    userId = userId,
    type = type,
    amount = amount,
    category = category,
    description = description,
    date = date,
    createdAt = createdAt,
)

fun Transaction.toEntity(): TransactionEntity = TransactionEntity(
    id = id,
    userId = userId,
    type = type,
    amount = amount,
    category = category,
    description = description,
    date = date,
    createdAt = createdAt,
)
