package com.beetlestance.spoonacular_kotlin.models.response

import com.squareup.moshi.Json

data class ResponseClassifyCuisine(

    @Json(name = "confidence")
    val confidence: Double? = null,

    @Json(name = "cuisine")
    val cuisine: String? = null,

    @Json(name = "cuisines")
    val cuisines: List<String?>? = null
)
