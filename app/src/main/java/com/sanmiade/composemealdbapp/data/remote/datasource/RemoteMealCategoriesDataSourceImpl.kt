package com.sanmiade.composemealdbapp.data.remote.datasource

import com.sanmiade.composemealdbapp.data.remote.MealDBService
import com.sanmiade.composemealdbapp.data.remote.response.mealCategories.MealCategoriesResponse
import javax.inject.Inject

class RemoteMealCategoriesDataSourceImpl @Inject constructor(private val mealDBService: MealDBService) :
    RemoteMealCategoriesDataSource {
    override suspend fun getCategories(): MealCategoriesResponse = mealDBService.getCategories()
}