package com.beetlestance.aphid.di

import com.beetlestance.aphid.BuildConfig
import com.beetlestance.base.utils.AppCoroutineDispatchers
import com.beetlestance.spoonacular.SpoonacularNetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideCoroutineDispatchers(): AppCoroutineDispatchers = AppCoroutineDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main
    )

    @Named("spoonacular-api-key")
    @Singleton
    @Provides
    fun provideSpoonacularApi() = BuildConfig.SpoonacularApiKey
}
