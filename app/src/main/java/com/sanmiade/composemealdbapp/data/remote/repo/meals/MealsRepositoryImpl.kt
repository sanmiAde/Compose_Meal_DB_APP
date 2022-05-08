package com.sanmiade.composemealdbapp.data.remote.repo.meals

import com.sanmiade.composemealdbapp.data.local.model.MealEntity
import com.sanmiade.composemealdbapp.data.remote.response.mealDetail.toDomain
import com.sanmiade.composemealdbapp.data.remote.response.mealDetail.toMealDomain
import com.sanmiade.composemealdbapp.data.remote.response.meals.toDomain
import com.sanmiade.composemealdbapp.domain.datasource.LocalMealsDataSource
import com.sanmiade.composemealdbapp.domain.datasource.RemoteMealsDataSource
import com.sanmiade.composemealdbapp.domain.model.MealDetailModel
import com.sanmiade.composemealdbapp.domain.model.MealModel
import com.sanmiade.composemealdbapp.domain.repo.MealsRepository
import com.sanmiade.composemealdbapp.utils.suspendRunCatching
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MealsRepositoryImpl @Inject constructor(
    private val remoteMealsDataSource: RemoteMealsDataSource,
    private val localMealsDataSource: LocalMealsDataSource
) :
    MealsRepository {
    override suspend fun getMeals(categoryName: String): Result<List<MealModel>> {
        return suspendRunCatching {
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

    override suspend fun searchMeals(name: String): Result<List<MealModel>> {
        return suspendRunCatching { remoteMealsDataSource.searchMeal(query = name).toMealDomain() }
    }

    override suspend fun getMeal(name: String): Result<MealDetailModel> {
        return suspendRunCatching {
            remoteMealsDataSource.searchMeal(query = name).toDomain().first()
        }
    }
}