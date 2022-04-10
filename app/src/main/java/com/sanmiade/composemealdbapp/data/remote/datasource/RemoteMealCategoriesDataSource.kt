package com.sanmiade.composemealdbapp.data.remote.datasource

import com.sanmiade.composemealdbapp.data.remote.response.mealCategories.MealCategoriesResponse

interface RemoteMealCategoriesDataSource {
    suspend fun getCategories(): MealCategoriesResponse
}