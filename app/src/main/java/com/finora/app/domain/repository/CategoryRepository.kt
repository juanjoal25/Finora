package com.finora.app.domain.repository

import com.finora.app.domain.model.Category
import com.finora.app.domain.model.TransactionType
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun observeCategories(type: TransactionType? = null): Flow<List<Category>>
}
