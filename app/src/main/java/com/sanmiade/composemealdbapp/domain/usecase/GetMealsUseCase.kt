package com.sanmiade.composemealdbapp.domain.usecase

import com.sanmiade.composemealdbapp.domain.AsyncDispatcher
import com.sanmiade.composemealdbapp.domain.model.MealModel
import com.sanmiade.composemealdbapp.domain.repo.MealsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GetMealsUseCase @Inject constructor(
    private val mealsRepository: MealsRepository,
    private val asyncDispatcher: AsyncDispatcher
) {
    suspend operator fun invoke(categoryName: String): Result<List<MealModel>> {
        return withContext(asyncDispatcher.io) {
            mealsRepository.getMeals(categoryName)
        }
    }
}