package com.sanmiade.composemealdbapp.domain.datasource

import com.sanmiade.composemealdbapp.data.local.model.MealEntity

interface LocalMealsDataSource {
    suspend fun getMeals() : List<MealEntity>
    suspend fun getMeal(id: String) : MealEntity?
    suspend fun saveMeal(mealEntity: MealEntity)
    suspend fun deleteMeal(mealEntity: MealEntity)
}