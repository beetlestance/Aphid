package com.beetlestance.spoonacular_kotlin.models.response.food.wine

import com.squareup.moshi.Json

data class DishPairingForWine(

    @Json(name = "text")
    val text: String? = null,

    @Json(name = "pairings")
    val pairings: List<String?>? = null
)
