package com.beetlestance.spoonacular_kotlin.models.response.food.wine

import com.squareup.moshi.Json

data class WineDescription(

    @Json(name = "wineDescription")
    val wineDescription: String? = null
)
