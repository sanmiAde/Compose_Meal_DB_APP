package com.sanmiade.composemealdbapp.data.remote

import com.sanmiade.composemealdbapp.data.remote.response.MealCategoriesResponse
import com.sanmiade.composemealdbapp.data.remote.response.MealCategory
import retrofit2.http.GET

interface MealDBService {
    @GET("api/json/v1/1/categories.php")
    suspend fun getCategories() : MealCategoriesResponse
}