package com.beetlestance.aphid.data.mapper

import com.beetlestance.aphid.data.entities.Recipe
import com.beetlestance.spoonacular_kotlin.models.response.recipe.RecipeInformation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeInformationToRecipe @Inject constructor() : Mapper<RecipeInformation, Recipe> {

    override suspend fun map(from: RecipeInformation) = Recipe(
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
