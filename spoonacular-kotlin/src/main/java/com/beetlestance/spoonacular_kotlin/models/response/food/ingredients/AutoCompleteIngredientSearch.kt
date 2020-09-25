package com.beetlestance.spoonacular_kotlin.models.response.food.ingredients

import com.squareup.moshi.Json

data class AutoCompleteIngredientSearch(

    @Json(name = "image")
    val image: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "possibleUnits")
    val possibleUnits: List<String?>? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "aisle")
    val aisle: String? = null
)
