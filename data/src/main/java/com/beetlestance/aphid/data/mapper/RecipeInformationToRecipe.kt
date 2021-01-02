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
package com.beetlestance.aphid.data.mapper

import com.beetlestance.aphid.data.entities.Recipe
import com.beetlestance.spoonacular_kotlin.models.response.recipe.RecipeInformation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeInformationToRecipe @Inject constructor() : Mapper<RecipeInformation, Recipe> {

    override suspend fun map(from: RecipeInformation): Recipe = Recipe(
        id = 0,
        recipeId = from.id,
        title = from.title,
        rating = from.spoonacularScore,
        serving = from.servings,
        readyInMinutes = from.readyInMinutes,
        calories = null,
        imageUrl = from.image,
        imageType = from.imageType
    )
}
