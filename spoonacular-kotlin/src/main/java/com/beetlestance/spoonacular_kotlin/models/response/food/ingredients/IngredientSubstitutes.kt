package com.beetlestance.spoonacular_kotlin.models.response.food.ingredients

import com.squareup.moshi.Json

data class IngredientSubstitutes(

    @Json(name = "ingredient")
    val ingredient: String? = null,

    @Json(name = "substitutes")
    val substitutes: List<String?>? = null,

    @Json(name = "message")
    val message: String? = null
)
