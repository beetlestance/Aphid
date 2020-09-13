package com.beetlestance.spoonacular_kotlin.models.request

import com.squareup.moshi.Json
import java.math.BigDecimal

/**
 * @param ingredientList The ingredient list of the recipe, one ingredient per line.
 * @param servings The number of servings that you can make from the ingredients.
 * @param includeNutrition Whether nutrition data should be added to correctly parsed ingredients. (optional)
 * */
data class RequestParseIngredients(

    @Json(name = "ingredientList")
    val ingredientList: String,

    @Json(name = "servings")
    val servings: BigDecimal,

    @Json(name = "includeNutrition")
    val includeNutrition: Boolean?
)
