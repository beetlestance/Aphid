package com.beetlestance.data.repositories.recipes

import com.beetlestance.data.daos.RecipeDao
import com.beetlestance.data.entities.Recipe
import javax.inject.Inject

class RecipesStore @Inject constructor(
    private val recipesDao: RecipeDao
) {

    fun observeRecipes() = recipesDao.recipes()

    suspend fun saveRecipes(recipes: List<Recipe>) =
        recipesDao.insertAll(recipes)
}
