package com.sanmiade.composemealdbapp.domain.repo

import com.sanmiade.composemealdbapp.domain.model.MealCategoryModel

interface MealCategoriesRepository {
    suspend fun getMealCategories(): Result<List<MealCategoryModel>>
}