package com.beetlestance.spoonacular_kotlin.models.response

import com.squareup.moshi.Json

data class ResponseSimilarRecipes(

    @Json(name = "readyInMinutes")
    val readyInMinutes: Int? = null,

    @Json(name = "sourceUrl")
    val sourceUrl: String? = null,

    @Json(name = "servings")
    val servings: Int? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "imageType")
    val imageType: String? = null
)
