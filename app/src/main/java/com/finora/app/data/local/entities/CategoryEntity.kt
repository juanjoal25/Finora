package com.finora.app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.finora.app.domain.model.TransactionType

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: String,
    val name: String,
    val type: TransactionType,
    val icon: String,
)
