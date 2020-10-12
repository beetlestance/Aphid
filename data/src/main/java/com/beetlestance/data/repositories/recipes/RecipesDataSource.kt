/*
 * Copyright 2020 BeetleStance
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
package com.beetlestance.data.repositories.recipes

import com.beetlestance.base.extensions.executeWithRetry
import com.beetlestance.base.extensions.toResult
import com.beetlestance.base.result.Result
import com.beetlestance.spoonacular_kotlin.models.response.recipe.RecipeInformation
import com.beetlestance.spoonacular_kotlin.services.RecipesService
import com.beetlestance.spoonacular_kotlin.utils.toSpoonacularApiResponse

class RecipesDataSource(
    private val recipesService: RecipesService
) {
    suspend fun fetchRecipes(): Result<List<RecipeInformation>> {
        return recipesService.getRandomRecipes()
            .executeWithRetry(shouldRetry = { true })
            .toSpoonacularApiResponse()
            .toResult()
    }
}
