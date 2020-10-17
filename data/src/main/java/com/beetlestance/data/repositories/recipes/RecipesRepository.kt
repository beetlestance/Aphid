package com.beetlestance.data.repositories.recipes

import com.beetlestance.base.result.dataOrThrowException
import com.beetlestance.spoonacular_kotlin.models.response.recipe.RecipeInformation
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    private val recipesDataSource: RecipesDataSource
) {
    suspend fun fetchRecipes(): List<RecipeInformation> {
        val recipeResult = recipesDataSource.fetchRecipes()
        return recipeResult.dataOrThrowException().recipes
    }
}