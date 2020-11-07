package com.beetlestance.aphid.data.repositories.recipes

import com.beetlestance.aphid.data.daos.RecipeDao
import com.beetlestance.aphid.data.entities.Recipe
import javax.inject.Inject

class RecipesStore @Inject constructor(
    private val recipesDao: RecipeDao
) {

    fun observeRecipes() = recipesDao.recipes()

    suspend fun saveRecipes(recipes: List<Recipe>) =
        recipesDao.insertAll(recipes)

    suspend fun numberOfRecipesSaved() = recipesDao.allRecipes().size

    suspend fun updateRecipe(recipe: Recipe) = recipesDao.update(entity = recipe)
}
