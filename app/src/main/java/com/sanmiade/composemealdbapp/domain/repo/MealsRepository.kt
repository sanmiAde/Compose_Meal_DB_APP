package com.sanmiade.composemealdbapp.domain.repo

import com.sanmiade.composemealdbapp.domain.model.MealModel

interface MealsRepository {
    suspend fun getMeals(categoryName: String) : Result<List<MealModel>>
}