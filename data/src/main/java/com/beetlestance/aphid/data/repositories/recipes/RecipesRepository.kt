package com.beetlestance.aphid.data.repositories.recipes

import com.beetlestance.aphid.base.result.dataOrThrowException
import com.beetlestance.aphid.data.entities.Recipe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    private val recipesDataSource: RecipesDataSource,
    private val recipesStore: RecipesStore
) {
    fun observeRecipes(): Flow<List<Recipe>> = recipesStore.observeRecipes()

    suspend fun fetchRecipes() {
        // For testing only, will remove once explore layer is created
        if (recipesStore.numberOfRecipesSaved() > 0) return
        val recipeResult = recipesDataSource.fetchBreakfast()
        recipeResult.dataOrThrowException(recipesStore::saveRecipes)
    }

    suspend fun updateRecipe(recipe: Recipe) {
        recipesStore.updateRecipe(recipe)
    }
}
