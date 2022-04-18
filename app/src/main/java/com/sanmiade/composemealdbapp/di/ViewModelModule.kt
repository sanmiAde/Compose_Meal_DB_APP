package com.sanmiade.composemealdbapp.di

import com.sanmiade.composemealdbapp.domain.repo.RemoteMealCategoriesDataSource
import com.sanmiade.composemealdbapp.data.remote.datasource.mealCategories.RemoteMealCategoriesDataSourceImpl
import com.sanmiade.composemealdbapp.data.remote.datasource.meals.RemoteMealsDataSourceImpl
import com.sanmiade.composemealdbapp.data.remote.repo.mealCategories.MealCategoriesRepositoryImpl
import com.sanmiade.composemealdbapp.data.remote.repo.meals.MealsRepositoryImpl
import com.sanmiade.composemealdbapp.domain.repo.MealCategoriesRepository
import com.sanmiade.composemealdbapp.domain.repo.MealsRepository
import com.sanmiade.composemealdbapp.domain.repo.RemoteMealsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Module
    @InstallIn(ViewModelComponent::class)
    abstract class ViewModelBindsModule {
        @Binds
        abstract fun bindRemoteMealCategoriesDataSource(
            remoteMealCategoriesDataSourceImpl: RemoteMealCategoriesDataSourceImpl
        ): RemoteMealCategoriesDataSource

        @Binds
        abstract fun bindCategoriesRepository(
            mealCategoriesRepositoryImpl: MealCategoriesRepositoryImpl
        ): MealCategoriesRepository

        @Binds
        abstract fun bindMealsRepository(
            mealsRepositoryImpl: MealsRepositoryImpl
        ): MealsRepository

        @Binds
        abstract fun bindRemoteMealsDataSource(
            remoteMealsDataSourceImpl: RemoteMealsDataSourceImpl
        ): RemoteMealsDataSource
    }

}