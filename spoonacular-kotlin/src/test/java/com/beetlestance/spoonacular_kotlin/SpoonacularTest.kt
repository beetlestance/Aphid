package com.beetlestance.spoonacular_kotlin

import com.google.common.truth.Truth
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.SECONDS

open class SpoonacularTest {

    val spoonacular: Spoonacular = provideSpoonacular()

    @Test
    fun validateApiKey() = Truth.assertWithMessage("No API Key provided").that(apiKey).isNotEmpty()

    companion object {

        private const val API_KEY_PARAM = "TEST_API_KEY"

        private val apiKey = System.getenv().getOrDefault(API_KEY_PARAM, "")

        @BeforeClass
        private fun provideSpoonacular(): Spoonacular {
            return object : Spoonacular(apiKey) {
                override fun okHttpClient(): OkHttpClient {
                    return okHttpClientBuilder().apply {
                        connectTimeout(20, SECONDS)
                        readTimeout(20, SECONDS)
                        writeTimeout(20, SECONDS)
                    }.build()
                }
            }
        }
    }

}