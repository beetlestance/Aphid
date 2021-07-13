package com.beetlestance.spoonacular_kotlin

import com.beetlestance.spoonacular_kotlin.services.RecipesService
import com.google.common.truth.Truth.assertWithMessage
import org.junit.Before
import org.junit.Test

class SpoonacularServiceTest {

    lateinit var recipesService: RecipesService

    private val spoonacular = Spoonacular("")
        .retrofitBuilder()
        .build()

    @Before
    fun setUpService() {
        recipesService = spoonacular.create(RecipesService::class.java)
    }

    @Test
    fun validateApiKey() {
        val api = System.getenv().getOrDefault(API_KEY_PARAM, "")
        assertWithMessage("No API Key provided").that(api).isNotEmpty()
    }

    companion object {
        const val API_KEY_PARAM = "TEST_API_KEY"
    }
}
