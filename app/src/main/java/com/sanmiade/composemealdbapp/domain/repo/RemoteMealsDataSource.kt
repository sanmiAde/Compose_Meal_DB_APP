package com.sanmiade.composemealdbapp.domain.repo

import com.sanmiade.composemealdbapp.data.remote.response.meals.MealsResponse

interface RemoteMealsDataSource {
    suspend fun getMeals(categoryName: String) : MealsResponse
}