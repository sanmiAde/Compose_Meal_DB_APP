package com.sanmiade.composemealdbapp.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sanmiade.composemealdbapp.BuildConfig
import com.sanmiade.composemealdbapp.data.remote.MealDBService
import com.sanmiade.composemealdbapp.domain.AsyncDispatcher
import com.sanmiade.composemealdbapp.domain.CoroutinesDispatcher
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesOkhttpClient(okHttpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(okHttpLoggingInterceptor)
        return client.build()
    }

    @Singleton
    @Provides
    fun providesOkHttpLogger(): HttpLoggingInterceptor {
        val okhttpLogger = HttpLoggingInterceptor()
        okhttpLogger.setLevel(HttpLoggingInterceptor.Level.BODY)
        return okhttpLogger
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
        val contentType = "application/json".toMediaType()
        return retrofit.addConverterFactory(Json.asConverterFactory(contentType))
            .baseUrl("https://www.themealdb.com/")
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providesMealsDbService(retrofit: Retrofit): MealDBService {
        return retrofit.create(MealDBService::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class AppBindModule {
        @Binds
        abstract fun bindAsyncDispatcher(coroutinesDispatcher: CoroutinesDispatcher) : AsyncDispatcher
    }
}