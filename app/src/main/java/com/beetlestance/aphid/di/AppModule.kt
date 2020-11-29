package com.beetlestance.aphid.di

import com.beetlestance.aphid.BuildConfig
import com.beetlestance.aphid.base.utils.AppCoroutineDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideCoroutineDispatchers(): AppCoroutineDispatchers = AppCoroutineDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main
    )

    @Named("spoonacular_api_key")
    @Singleton
    @Provides
    fun provideSpoonacularApi() = BuildConfig.SpoonacularApiKey
}
