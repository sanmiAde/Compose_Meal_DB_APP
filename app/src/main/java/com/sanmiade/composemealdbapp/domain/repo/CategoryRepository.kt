package com.sanmiade.composemealdbapp.domain.repo

import com.sanmiade.composemealdbapp.domain.model.MealCategoryModel

interface CategoryRepository {
    suspend fun getMealCategories() : Result<List<MealCategoryModel>>
}