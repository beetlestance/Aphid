package com.beetlestance.spoonacular_kotlin.models.response

import com.squareup.moshi.Json

data class ResponseSearchRecipesByNutrients(

    @Json(name = "image")
    val image: String? = null,

    @Json(name = "carbs")
    val carbs: String? = null,

    @Json(name = "protein")
    val protein: String? = null,

    @Json(name = "fat")
    val fat: String? = null,

    @Json(name = "calories")
    val calories: Int? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "imageType")
    val imageType: String? = null
)
