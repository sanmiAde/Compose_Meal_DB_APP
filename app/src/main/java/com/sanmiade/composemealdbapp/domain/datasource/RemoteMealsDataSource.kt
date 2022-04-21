package com.sanmiade.composemealdbapp.domain.datasource

import com.sanmiade.composemealdbapp.data.remote.response.mealDetail.MealDetailResponses
import com.sanmiade.composemealdbapp.data.remote.response.meals.MealResponses

interface RemoteMealsDataSource {
    suspend fun getMeals(categoryName: String) : MealResponses
    suspend fun searchMeal(query: String) : MealDetailResponses
}