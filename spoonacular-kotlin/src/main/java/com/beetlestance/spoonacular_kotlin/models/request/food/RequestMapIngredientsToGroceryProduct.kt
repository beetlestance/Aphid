package com.beetlestance.spoonacular_kotlin.models.request.food

import com.squareup.moshi.Json

/**
 * @param ingredients The title of the recipe.
 * @param servings The ingredient list of the recipe, one ingredient per line (separate lines with \n).
 */
data class RequestMapIngredientsToGroceryProduct(

    @Json(name = "ingredients")
    val ingredients: List<String>,

    @Json(name = "servings")
    val servings: Int

)
