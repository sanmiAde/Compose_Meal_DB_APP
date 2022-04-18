package com.sanmiade.composemealdbapp.data.remote.datasource.mealCategories

import com.sanmiade.composemealdbapp.data.remote.MealDBService
import com.sanmiade.composemealdbapp.data.remote.response.mealCategories.MealCategoriesResponse
import com.sanmiade.composemealdbapp.domain.repo.RemoteMealCategoriesDataSource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RemoteMealCategoriesDataSourceImpl @Inject constructor(private val mealDBService: MealDBService) :
    RemoteMealCategoriesDataSource {
    override suspend fun getCategories(): MealCategoriesResponse = mealDBService.getCategories()
}