package com.finora.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.finora.app.data.local.dao.CategoryDao
import com.finora.app.data.local.dao.TransactionDao
import com.finora.app.data.local.dao.UserDao
import com.finora.app.data.local.entities.CategoryEntity
import com.finora.app.data.local.entities.TransactionEntity
import com.finora.app.data.local.entities.UserEntity

@Database(
    entities = [UserEntity::class, TransactionEntity::class, CategoryEntity::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
}
