package com.beetlestance.data.repositories.recipes

import com.beetlestance.data.daos.RecipesDao
import com.beetlestance.data.entities.Recipes
import javax.inject.Inject

class RecipesStore @Inject constructor(
    private val recipesDao: RecipesDao
) {

    fun observeRecipes() = recipesDao.recipes()

    suspend fun saveRecipes(recipes: List<Recipes>) =
        recipesDao.insertAll(recipes)
}