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
package com.beetlestance.aphid.data.repositories.recipes

import com.beetlestance.aphid.base.extensions.executeWithRetry
import com.beetlestance.aphid.base.result.Result
import com.beetlestance.aphid.data.entities.Recipe
import com.beetlestance.aphid.data.mapper.RecipeInformationToRecipe
import com.beetlestance.aphid.data.mapper.forLists
import com.beetlestance.aphid.data.toResult
import com.beetlestance.spoonacular_kotlin.models.response.recipe.RecipeInformation
import com.beetlestance.spoonacular_kotlin.models.response.recipe.SearchRecipeComplex
import com.beetlestance.spoonacular_kotlin.services.RecipesService
import com.beetlestance.spoonacular_kotlin.utils.serializedCopy
import com.beetlestance.spoonacular_kotlin.utils.serializedMapper
import com.beetlestance.spoonacular_kotlin.utils.serializedTransform
import com.beetlestance.spoonacular_kotlin.utils.toSpoonacularApiResponse
import javax.inject.Inject

class RecipesDataSource @Inject constructor(
    private val recipesService: RecipesService,
    private val recipeInformationToRecipe: RecipeInformationToRecipe
) {

    suspend fun fetchRecipes(): Result<List<Recipe>> {
        return recipesService.getRandomRecipes(number = 20)
            .executeWithRetry(shouldRetry = { false })
            .toSpoonacularApiResponse()
            .toResult { (recipes) -> recipeInformationToRecipe.forLists().invoke(recipes) }
    }

    suspend fun fetchBreakfast(): Result<List<Recipe>> {
        return recipesService.searchRecipesComplex(
            query = "breakfast",
            number = 10
        ).executeWithRetry(shouldRetry = { false })
            .toSpoonacularApiResponse()
            .toResult { complexRecipe ->
                val recipes: List<RecipeInformation> =
                    complexRecipe.results?.serializedMapper<SearchRecipeComplex.ResultsItem?, RecipeInformation, RecipeInformation> { it }
                        ?: emptyList()
                recipeInformationToRecipe.forLists().invoke(recipes)
            }
    }
}
