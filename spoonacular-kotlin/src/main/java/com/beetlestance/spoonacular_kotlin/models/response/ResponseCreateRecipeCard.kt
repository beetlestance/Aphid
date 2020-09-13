package com.beetlestance.spoonacular_kotlin.models.response

import com.squareup.moshi.Json

data class ResponseCreateRecipeCard(

    @Json(name = "url")
    val url: String? = null
)
