package com.beetlestance.spoonacular_kotlin.models.response.food.product

import com.squareup.moshi.Json

data class ClassifyGroceryProduct(

    @Json(name = "cleanTitle")
    val cleanTitle: String? = null,

    @Json(name = "image")
    val image: String? = null,

    @Json(name = "usdaCode")
    val usdaCode: Int? = null,

    @Json(name = "category")
    val category: String? = null,

    @Json(name = "breadcrumbs")
    val breadcrumbs: List<String?>? = null
)
