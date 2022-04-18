package com.sanmiade.composemealdbapp.data.remote

import com.sanmiade.composemealdbapp.data.remote.response.mealCategories.MealCategoriesResponse
import com.sanmiade.composemealdbapp.data.remote.response.meals.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MealDBService {
    @GET("api/json/v1/1/categories.php")
    suspend fun getCategories(): MealCategoriesResponse

    @GET("/api/json/v1/1/filter.php")
    suspend fun getMeals(@Query("c") category: String) : MealsResponse
}