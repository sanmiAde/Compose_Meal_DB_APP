package com.sanmiade.composemealdbapp.data.remote.datasource.meals

import com.sanmiade.composemealdbapp.data.remote.MealDBService
import com.sanmiade.composemealdbapp.data.remote.response.meals.MealsResponse
import com.sanmiade.composemealdbapp.domain.repo.RemoteMealsDataSource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RemoteMealsDataSourceImpl @Inject constructor(private val mealDBService: MealDBService) :
    RemoteMealsDataSource {
    override suspend fun getMeals(categoryName: String): MealsResponse {
        return mealDBService.getMeals(categoryName)
    }
}