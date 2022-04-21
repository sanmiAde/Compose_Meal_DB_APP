package com.sanmiade.composemealdbapp.data.local.datasource

import com.sanmiade.composemealdbapp.data.local.dao.MealDao
import com.sanmiade.composemealdbapp.data.local.model.MealEntity
import com.sanmiade.composemealdbapp.domain.datasource.LocalMealsDataSource
import javax.inject.Inject

class LocalMealsDataSourceImpl @Inject constructor(private val mealDao: MealDao) :
    LocalMealsDataSource {
    override suspend fun getMeals(): List<MealEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getMeal(id: String): MealEntity? {
        return mealDao.getMeal(id)
    }

    override suspend fun saveMeal(mealEntity: MealEntity) {
        mealDao.saveMeal(mealEntity)
    }

    override suspend fun deleteMeal(mealEntity: MealEntity) {
        mealDao.deleteMeal(mealEntity)
    }
}