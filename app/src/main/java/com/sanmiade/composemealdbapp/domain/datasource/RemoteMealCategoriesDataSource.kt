package com.sanmiade.composemealdbapp.domain.datasource

import com.sanmiade.composemealdbapp.data.remote.response.mealCategories.MealCategoriesResponse

interface RemoteMealCategoriesDataSource {
    suspend fun getCategories(): MealCategoriesResponse
}