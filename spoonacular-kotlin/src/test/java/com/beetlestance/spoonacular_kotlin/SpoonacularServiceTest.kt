package com.beetlestance.spoonacular_kotlin

import com.beetlestance.spoonacular_kotlin.services.RecipesService
import org.junit.Before

class SpoonacularServiceTest {

    lateinit var recipesService: RecipesService

    private val spoonacular = Spoonacular("")
        .retrofitBuilder()
        .build()

    @Before
    fun setUpService() {
        recipesService = spoonacular.create(RecipesService::class.java)
    }


}