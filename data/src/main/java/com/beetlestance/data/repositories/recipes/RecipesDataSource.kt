package com.beetlestance.data.repositories.recipes

import com.beetlestance.base.extensions.executeWithRetry
import com.beetlestance.base.extensions.toResult
import com.beetlestance.base.result.Result
import com.beetlestance.spoonacular_kotlin.models.response.recipe.RecipeInformation
import com.beetlestance.spoonacular_kotlin.services.RecipesService
import com.beetlestance.spoonacular_kotlin.utils.toSpoonacularApiResponse

class RecipesDataSource(
    private val recipesService: RecipesService
) {
    suspend fun fetchRecipes(): Result<List<RecipeInformation>> {
        return recipesService.getRandomRecipes()
            .executeWithRetry(shouldRetry = { true })
            .toSpoonacularApiResponse()
            .toResult()
    }
}