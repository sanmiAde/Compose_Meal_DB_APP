package com.sanmiade.composemealdbapp.util

import com.sanmiade.composemealdbapp.data.remote.response.mealCategories.MealCategoriesResponse
import com.sanmiade.composemealdbapp.domain.AsyncDispatcher
import com.sanmiade.composemealdbapp.domain.CoroutinesDispatcher
import com.sanmiade.composemealdbapp.domain.model.MealCategoryModel
import com.sanmiade.composemealdbapp.domain.usecase.GetMealCategoriesUseCase

object Fixture {
    private val kotlinFixture = com.appmattus.kotlinfixture.kotlinFixture()

    fun createMealCategoriesResponse() = kotlinFixture<MealCategoriesResponse>()

    fun createMealCategoryModels() = kotlinFixture<List<MealCategoryModel>>()

    fun createAsyncDispatcher() = CoroutinesDispatcher()
}