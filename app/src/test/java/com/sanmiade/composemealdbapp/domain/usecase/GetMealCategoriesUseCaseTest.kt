package com.sanmiade.composemealdbapp.domain.usecase

import com.google.common.truth.Truth
import com.sanmiade.composemealdbapp.fake.FakeMealCategoriesRepository
import com.sanmiade.composemealdbapp.util.Fixture
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class GetMealCategoriesUseCaseTest {
    @Test
    fun mustReturnMealCategoriesWhenRequestIsSuccessful() = runTest {
        //Arrange
        val fakeMealCategoriesRepository = FakeMealCategoriesRepository(true)
        val getMealCategoriesUseCase = GetMealCategoriesUseCase(
            fakeMealCategoriesRepository,
            Fixture.createAsyncDispatcher()
        )
        //Act
        val result = getMealCategoriesUseCase().getOrThrow()

        //Assert
        Truth.assertThat(result.isNotEmpty()).isTrue()
    }

    @Test
    fun mustReturnFailureWhenRequestFailed() = runTest {
        //Arrange
        val fakeMealCategoriesRepository = FakeMealCategoriesRepository(false)
        val getMealCategoriesUseCase = GetMealCategoriesUseCase(
            fakeMealCategoriesRepository,
            Fixture.createAsyncDispatcher()
        )
        //Act
        val result = getMealCategoriesUseCase().exceptionOrNull()

        //Assert
        Truth.assertThat(result).isInstanceOf(IOException::class.java)
    }
}