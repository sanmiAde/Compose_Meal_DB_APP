package com.sanmiade.composemealdbapp.domain.repo

import com.sanmiade.composemealdbapp.domain.model.MealModel

interface MealsRepository {
    suspend fun getMeals(categoryName: String): Result<List<MealModel>>
    suspend fun getSavedMeals(): List<MealModel>
    suspend fun isMealSaved(id: String) : Boolean
    suspend fun saveMeal(mealModel: MealModel)
    suspend fun deleteMeal(mealModel: MealModel)
    suspend fun searchMeals(name: String): Result<List<MealModel>>
}