package com.sanmiade.composemealdbapp.domain.usecase

import com.sanmiade.composemealdbapp.domain.AsyncDispatcher
import com.sanmiade.composemealdbapp.domain.model.MealCategoryModel
import com.sanmiade.composemealdbapp.domain.repo.CategoryRepository
import kotlinx.coroutines.withContext

class GetMealCategoryUseCase constructor(
    private val categoryRepository: CategoryRepository,
    private val asyncDispatcher: AsyncDispatcher
) {
    suspend operator fun invoke(): Result<List<MealCategoryModel>> =
        withContext(asyncDispatcher.io) {
            categoryRepository.getMealCategories()
        }
}