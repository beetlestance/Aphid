package com.beetlestance.spoonacular_kotlin.models.request

import com.squareup.moshi.Json

/**
 * @param title The title of the recipe.
 * @param ingredientList The ingredient list of the recipe, one ingredient per line (separate lines with \n).
 */
data class RequestClassifyCuisine(

    @Json(name = "title")
    val title: String,

    @Json(name = "ingredientList")
    val ingredientList: String
)
