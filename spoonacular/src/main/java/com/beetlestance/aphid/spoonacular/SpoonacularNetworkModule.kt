/*
 * Copyright 2021 BeetleStance
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beetlestance.aphid.spoonacular

import com.beetlestance.aphid.spoonacular.kotlin.Spoonacular
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
    ): Spoonacular = object : Spoonacular(apiKey) {
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
