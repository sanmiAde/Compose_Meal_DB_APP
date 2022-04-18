package com.sanmiade.composemealdbapp.domain.repo

import com.sanmiade.composemealdbapp.data.remote.response.mealCategories.MealCategoriesResponse

interface RemoteMealCategoriesDataSource {
    suspend fun getCategories(): MealCategoriesResponse
}