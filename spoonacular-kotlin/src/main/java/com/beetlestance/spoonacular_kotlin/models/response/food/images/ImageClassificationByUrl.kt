package com.beetlestance.spoonacular_kotlin.models.response.food.images

import com.squareup.moshi.Json

data class ImageClassificationByUrl(

    @Json(name = "probability")
    val probability: Double? = null,

    @Json(name = "category")
    val category: String? = null
)
