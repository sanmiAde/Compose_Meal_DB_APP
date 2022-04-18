package com.sanmiade.composemealdbapp.data.remote.repo.meals

import com.sanmiade.composemealdbapp.data.remote.response.meals.toDomain
import com.sanmiade.composemealdbapp.domain.model.MealModel
import com.sanmiade.composemealdbapp.domain.repo.MealsRepository
import com.sanmiade.composemealdbapp.domain.repo.RemoteMealsDataSource
import com.sanmiade.composemealdbapp.utils.runSuspendCatching
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MealsRepositoryImpl @Inject constructor(private val remoteMealsDataSource: RemoteMealsDataSource) :
    MealsRepository {
    override suspend fun getMeals(categoryName: String): Result<List<MealModel>> {
        return runSuspendCatching {
            remoteMealsDataSource.getMeals(categoryName = categoryName).toDomain()
        }
    }
}