package com.sanmiade.composemealdbapp.di

import com.sanmiade.composemealdbapp.data.remote.datasource.RemoteMealCategoriesDataSource
import com.sanmiade.composemealdbapp.data.remote.datasource.RemoteMealCategoriesDataSourceImpl
import com.sanmiade.composemealdbapp.data.remote.repo.MealCategoriesRepositoryImpl
import com.sanmiade.composemealdbapp.domain.repo.MealCategoriesRepository
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
    }

}