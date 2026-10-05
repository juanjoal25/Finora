package com.finora.app.data.repository

import com.finora.app.core.constants.DefaultCategories
import com.finora.app.data.local.dao.CategoryDao
import com.finora.app.data.local.entities.CategoryEntity
import com.finora.app.data.mapper.toDomain
import com.finora.app.domain.model.Category
import com.finora.app.domain.model.TransactionType
import com.finora.app.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
) : CategoryRepository {

    override fun observeCategories(type: TransactionType?): Flow<List<Category>> = flow {
        ensureSeeded()
        val source = if (type == null) categoryDao.observeAll() else categoryDao.observeByType(type)
        emitAll(source.map { list -> list.map { it.toDomain() } })
    }

    private suspend fun ensureSeeded() {
        if (categoryDao.count() == 0) {
            val seed = DefaultCategories.ALL.map {
                CategoryEntity(id = UUID.randomUUID().toString(), name = it.name, type = it.type, icon = it.icon)
            }
            categoryDao.insertAll(seed)
        }
    }
}
