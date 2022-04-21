package com.sanmiade.composemealdbapp.data.remote

import com.sanmiade.composemealdbapp.data.remote.response.mealCategories.MealCategoriesResponse
import com.sanmiade.composemealdbapp.data.remote.response.mealDetail.MealDetailResponses
import com.sanmiade.composemealdbapp.data.remote.response.meals.MealResponses
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDBService {
    @GET("api/json/v1/1/categories.php")
    suspend fun getCategories(): MealCategoriesResponse

    @GET("/api/json/v1/1/filter.php")
    suspend fun getMeals(@Query("c") category: String) : MealResponses

    @GET("/api/json/v1/1/search.php")
    suspend fun searchMeal(@Query("s")query: String) : MealDetailResponses
}