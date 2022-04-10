package com.sanmiade.composemealdbapp.domain.usecase

import com.sanmiade.composemealdbapp.domain.AsyncDispatcher
import com.sanmiade.composemealdbapp.domain.model.MealCategoryModel
import com.sanmiade.composemealdbapp.domain.repo.MealCategoriesRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GetMealCategoriesUseCase @Inject constructor(
    private val categoryRepository: MealCategoriesRepository,
    private val asyncDispatcher: AsyncDispatcher
) {
    suspend operator fun invoke(): Result<List<MealCategoryModel>> =
        withContext(asyncDispatcher.io) {
            categoryRepository.getMealCategories()
        }
}