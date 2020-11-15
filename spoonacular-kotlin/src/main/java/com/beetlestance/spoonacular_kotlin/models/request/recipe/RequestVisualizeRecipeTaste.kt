package com.beetlestance.spoonacular_kotlin.models.request.recipe

import com.squareup.moshi.Json


/**
 * @param ingredientList The ingredient list of the recipe, one ingredient per line.
 * */

data class RequestVisualizeRecipeTaste(

    @Json(name = "ingredientList")
    val ingredientList: String
)
