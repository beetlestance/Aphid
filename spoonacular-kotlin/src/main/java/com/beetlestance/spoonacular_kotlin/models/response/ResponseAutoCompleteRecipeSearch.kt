package com.beetlestance.spoonacular_kotlin.models.response

import com.squareup.moshi.Json

data class ResponseAutoCompleteRecipeSearch(

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "imageType")
    val imageType: String? = null
)
