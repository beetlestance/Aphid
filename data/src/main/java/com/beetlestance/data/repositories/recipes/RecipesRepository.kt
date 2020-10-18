package com.beetlestance.data.repositories.recipes

import com.beetlestance.base.result.dataOrThrowException
import com.beetlestance.spoonacular_kotlin.models.response.recipe.RecipeInformation
import java.awt.print.PrinterAbortException
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    private val recipesDataSource: RecipesDataSource,
    private val recipesStore: RecipesStore
) {
    fun observeRecipes() = recipesStore.observeRecipes()

    suspend fun fetchRecipes() {
        // For testing only, will remove once explore layer is created
        if (recipesStore.numberOfRecipesSaved() > 0) return
        val recipeResult = recipesDataSource.fetchRecipes()
        recipeResult.dataOrThrowException(recipesStore::saveRecipes)
    }
}