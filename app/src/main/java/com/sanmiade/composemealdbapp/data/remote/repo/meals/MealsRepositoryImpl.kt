package com.sanmiade.composemealdbapp.data.remote.repo.meals

import com.sanmiade.composemealdbapp.data.local.model.MealEntity
import com.sanmiade.composemealdbapp.data.remote.response.meals.toDomain
import com.sanmiade.composemealdbapp.domain.model.MealModel
import com.sanmiade.composemealdbapp.domain.repo.LocalMealsDataSource
import com.sanmiade.composemealdbapp.domain.repo.MealsRepository
import com.sanmiade.composemealdbapp.domain.repo.RemoteMealsDataSource
import com.sanmiade.composemealdbapp.utils.runSuspendCatching
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MealsRepositoryImpl @Inject constructor(
    private val remoteMealsDataSource: RemoteMealsDataSource,
    private val localMealsDataSource: LocalMealsDataSource
) :
    MealsRepository {
    override suspend fun getMeals(categoryName: String): Result<List<MealModel>> {
        return runSuspendCatching {
            remoteMealsDataSource.getMeals(categoryName = categoryName).toDomain()
        }
    }

    override suspend fun getSavedMeals(): List<MealModel> {
        TODO("Not yet implemented")
    }

    override suspend fun isMealSaved(id: String): Boolean {
        return localMealsDataSource.getMeal(id) != null
    }

    override suspend fun saveMeal(mealModel: MealModel) {
        MealEntity.fromDomain(mealModel).run {
            localMealsDataSource.saveMeal(this)
        }
    }

    override suspend fun deleteMeal(mealModel: MealModel) {
        MealEntity.fromDomain(mealModel).run {
            localMealsDataSource.deleteMeal(this)
        }
    }
}