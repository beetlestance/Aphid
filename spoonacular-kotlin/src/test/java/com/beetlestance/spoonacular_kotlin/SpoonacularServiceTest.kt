package com.beetlestance.spoonacular_kotlin

import com.beetlestance.spoonacular_kotlin.services.RecipesService
import com.google.common.truth.Truth.assertThat
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
        val api = System.getenv()["TEST_API_KEY"]
        assertThat(api.isNullOrBlank().not())
        print(api)
    }

}