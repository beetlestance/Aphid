package com.beetlestance.aphid.di

import com.beetlestance.base.utils.AppCoroutineDispatchers
import com.beetlestance.spoonacular.SpoonacularNetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module(
    includes = [
        SpoonacularNetworkModule::class
    ]
)
object AppModule {

    @Singleton
    @Provides
    fun provideCoroutineDispatchers() = AppCoroutineDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main
    )
}
