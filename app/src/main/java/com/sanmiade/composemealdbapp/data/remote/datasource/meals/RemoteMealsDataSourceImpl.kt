package com.sanmiade.composemealdbapp.data.remote.datasource.meals

import com.sanmiade.composemealdbapp.data.remote.MealDBService
import com.sanmiade.composemealdbapp.data.remote.response.mealDetail.MealDetailResponses
import com.sanmiade.composemealdbapp.data.remote.response.meals.MealResponses
import com.sanmiade.composemealdbapp.domain.datasource.RemoteMealsDataSource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RemoteMealsDataSourceImpl @Inject constructor(private val mealDBService: MealDBService) :
    RemoteMealsDataSource {
    override suspend fun getMeals(categoryName: String): MealResponses {
        return mealDBService.getMeals(categoryName)
    }

    override suspend fun searchMeal(query: String): MealDetailResponses {
        return mealDBService.searchMeal(query = query)
    }
}