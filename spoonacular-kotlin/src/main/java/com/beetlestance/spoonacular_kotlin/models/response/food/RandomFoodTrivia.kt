package com.beetlestance.spoonacular_kotlin.models.response.food

import com.squareup.moshi.Json

data class RandomFoodTrivia(

    @Json(name = "text")
    val text: String? = null
)
