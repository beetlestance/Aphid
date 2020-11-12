package com.beetlestance.aphid.data.repositories.recipes

import com.beetlestance.aphid.data.daos.RecipeDao
import com.beetlestance.aphid.data.entities.Recipe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipesStore @Inject constructor(
    private val recipesDao: RecipeDao
) {

    fun observeRecipes(): Flow<List<Recipe>> = recipesDao.recipesObservable()

    fun observeRecentlyViewedRecipes(limit: Int): Flow<List<Recipe>> =
        recipesDao.favouriteRecipesObservable(limit)

    fun observeRecipesWithReadyTime(time: Long): Flow<List<Recipe>> =
        recipesDao.quickRecipesObservable(time)

    suspend fun saveRecipes(recipes: List<Recipe>): Unit = recipesDao.insertAll(recipes)

    suspend fun numberOfRecipesSaved(): Int = recipesDao.allRecipes().size

    suspend fun updateRecipe(recipe: Recipe): Unit = recipesDao.update(entity = recipe)
}
