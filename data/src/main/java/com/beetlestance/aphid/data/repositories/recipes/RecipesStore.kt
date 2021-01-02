/*
 * Copyright 2021 BeetleStance
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
        recipesDao.recentlyViewedRecipesObservable(limit)

    fun observeFavouriteRecipes(): Flow<List<Recipe>> = recipesDao.favouriteRecipesObservable()

    fun observeSavedRecipes(): Flow<List<Recipe>> = recipesDao.savedRecipesObservable()

    fun observeRecipesWithReadyTime(time: Long): Flow<List<Recipe>> =
        recipesDao.quickRecipesObservable(time)

    suspend fun saveRecipes(recipes: List<Recipe>): Unit = recipesDao.insertAll(recipes)

    suspend fun numberOfRecipesSaved(): Int = recipesDao.allRecipes().size

    suspend fun updateRecipe(recipe: Recipe): Unit = recipesDao.update(entity = recipe)
}
