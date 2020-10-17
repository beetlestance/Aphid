package com.beetlestance.spoonacular_kotlin.models.response.recipe

import com.squareup.moshi.Json

data class RandomRecipes(
    @Json(name = "recipes")
    val recipes: List<RecipeInformation>
)
