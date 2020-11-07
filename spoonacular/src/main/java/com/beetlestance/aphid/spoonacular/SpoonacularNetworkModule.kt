package com.beetlestance.aphid.spoonacular

import com.beetlestance.spoonacular_kotlin.Spoonacular
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [SpoonacularServiceModule::class])
object SpoonacularNetworkModule {

    @Provides
    @Singleton
    fun provideSpoonacular(
        client: OkHttpClient,
        @Named("spoonacular_api_key") apiKey: String
    ): Spoonacular =
        object : Spoonacular(apiKey) {
            override fun okHttpClient(): OkHttpClient {
                return client.newBuilder().apply {
                    setOkHttpClientDefaults()
                    connectTimeout(20, TimeUnit.SECONDS)
                    readTimeout(20, TimeUnit.SECONDS)
                    writeTimeout(20, TimeUnit.SECONDS)
                }.build()
            }
        }
}
