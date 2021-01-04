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