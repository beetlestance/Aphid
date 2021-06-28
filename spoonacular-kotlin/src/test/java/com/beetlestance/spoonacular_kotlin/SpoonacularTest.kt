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
package com.beetlestance.spoonacular_kotlin

import com.google.common.truth.Truth
import okhttp3.OkHttpClient
import org.junit.Test
import java.util.concurrent.TimeUnit.SECONDS

open class SpoonacularTest {

    private val apiKey = System.getenv().getOrDefault(API_KEY_PARAM, "")

    val spoonacular: Spoonacular = object : Spoonacular(apiKey) {
        override fun okHttpClient(): OkHttpClient {
            return okHttpClientBuilder().apply {
                connectTimeout(20, SECONDS)
                readTimeout(20, SECONDS)
                writeTimeout(20, SECONDS)
            }.build()
        }
    }

    @Test
    fun validateApiKey() = Truth.assertWithMessage("No API Key provided").that(apiKey).isNotEmpty()

    companion object {

        private const val API_KEY_PARAM = "TEST_API_KEY"
    }
}
