package com.sanmiade.composemealdbapp.fake

import com.sanmiade.composemealdbapp.domain.model.MealCategoryModel
import com.sanmiade.composemealdbapp.domain.repo.MealCategoriesRepository
import com.sanmiade.composemealdbapp.util.Fixture
import java.io.IOException

class FakeMealCategoriesRepository(private val isSuccessful: Boolean) : MealCategoriesRepository {
    override suspend fun getMealCategories(): Result<List<MealCategoryModel>> {
        return if (isSuccessful) {
            Result.success(Fixture.createMealCategoryModels())
        } else {
            Result.failure(IOException())
        }
    }
}