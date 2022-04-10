package com.sanmiade.composemealdbapp.data.remote.datasource

import com.sanmiade.composemealdbapp.data.remote.response.MealCategoriesResponse

interface RemoteMealCategoriesDataSource {
    suspend fun getCategories(): MealCategoriesResponse
}