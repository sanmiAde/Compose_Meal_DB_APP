package com.sanmiade.composemealdbapp.data.remote.repo.mealCategories

import com.sanmiade.composemealdbapp.domain.datasource.RemoteMealCategoriesDataSource
import com.sanmiade.composemealdbapp.data.remote.response.mealCategories.toDomain
import com.sanmiade.composemealdbapp.domain.model.MealCategoryModel
import com.sanmiade.composemealdbapp.domain.repo.MealCategoriesRepository
import com.sanmiade.composemealdbapp.utils.runSuspendCatching
import javax.inject.Inject

class MealCategoriesRepositoryImpl @Inject constructor(private val remoteMealCategoriesDataSource: RemoteMealCategoriesDataSource) :
    MealCategoriesRepository {
    override suspend fun getMealCategories(): Result<List<MealCategoryModel>> {
        return runSuspendCatching {
            remoteMealCategoriesDataSource.getCategories().toDomain()
        }
    }
}