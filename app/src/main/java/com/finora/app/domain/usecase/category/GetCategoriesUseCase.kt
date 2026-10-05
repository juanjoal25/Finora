package com.finora.app.domain.usecase.category

import com.finora.app.domain.model.Category
import com.finora.app.domain.model.TransactionType
import com.finora.app.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {
    operator fun invoke(type: TransactionType? = null): Flow<List<Category>> =
        categoryRepository.observeCategories(type)
}
