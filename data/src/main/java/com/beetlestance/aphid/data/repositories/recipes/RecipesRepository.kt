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

    fun observeRecentlyViewedRecipes(limit: Int): Flow<List<Recipe>> =
        recipesStore.observeRecentlyViewedRecipes(limit)

    fun observeRecipesWithReadyTime(time: Long): Flow<List<Recipe>> =
        recipesStore.observeRecipesWithReadyTime(time)

    suspend fun fetchRecipes(
        maxReadyTime: Long?,
        type: String?,
        numberOfRecipes: Int
    ) {
        val dbRecipes = recipesStore.numberOfRecipesSaved()
        if (dbRecipes > 0) return

        val recipeResult = recipesDataSource.fetchRecipes(
            maxReadyTime = maxReadyTime,
            type = type,
            numberOfRecipes = numberOfRecipes
        )
        recipeResult.dataOrThrowException(recipesStore::saveRecipes)
    }

    suspend fun updateRecipe(recipe: Recipe) {
        recipesStore.updateRecipe(recipe)
    }
}
