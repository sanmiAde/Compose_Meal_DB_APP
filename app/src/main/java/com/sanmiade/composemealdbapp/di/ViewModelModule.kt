package com.sanmiade.composemealdbapp.di

import com.sanmiade.composemealdbapp.data.local.ComposeMealDatabase
import com.sanmiade.composemealdbapp.data.local.dao.MealDao
import com.sanmiade.composemealdbapp.data.local.datasource.LocalMealsDataSourceImpl
import com.sanmiade.composemealdbapp.data.remote.datasource.mealCategories.RemoteMealCategoriesDataSourceImpl
import com.sanmiade.composemealdbapp.data.remote.datasource.meals.RemoteMealsDataSourceImpl
import com.sanmiade.composemealdbapp.data.remote.repo.mealCategories.MealCategoriesRepositoryImpl
import com.sanmiade.composemealdbapp.data.remote.repo.meals.MealsRepositoryImpl
import com.sanmiade.composemealdbapp.domain.repo.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun providesMealDao(composeMealDatabase: ComposeMealDatabase): MealDao {
        return composeMealDatabase.mealDao()
    }

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

        @Binds
        abstract fun bindLocalMealsDataSource(
            localMealsDataSourceImpl: LocalMealsDataSourceImpl
        ): LocalMealsDataSource
    }

}