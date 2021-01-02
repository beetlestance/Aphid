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

    fun observeFavouriteRecipes(): Flow<List<Recipe>> = recipesStore.observeFavouriteRecipes()

    fun observeSavedRecipes(): Flow<List<Recipe>> = recipesStore.observeSavedRecipes()

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
