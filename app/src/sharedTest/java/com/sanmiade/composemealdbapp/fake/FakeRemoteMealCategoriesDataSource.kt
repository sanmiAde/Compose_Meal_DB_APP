package com.sanmiade.composemealdbapp.fake

import com.sanmiade.composemealdbapp.domain.repo.RemoteMealCategoriesDataSource
import com.sanmiade.composemealdbapp.data.remote.response.mealCategories.MealCategoriesResponse
import com.sanmiade.composemealdbapp.util.Fixture

class FakeRemoteMealCategoriesDataSource : RemoteMealCategoriesDataSource {
    override suspend fun getCategories(): MealCategoriesResponse {
        return Fixture.createMealCategoriesResponse()
    }
}