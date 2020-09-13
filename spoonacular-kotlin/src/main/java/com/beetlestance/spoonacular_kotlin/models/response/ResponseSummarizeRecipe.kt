package com.beetlestance.spoonacular_kotlin.models.response

import com.squareup.moshi.Json

data class ResponseSummarizeRecipe(

    @Json(name = "summary")
    val summary: String? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "title")
    val title: String? = null
)
