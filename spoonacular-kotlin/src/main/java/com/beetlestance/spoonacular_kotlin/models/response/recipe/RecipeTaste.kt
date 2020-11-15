package com.beetlestance.spoonacular_kotlin.models.response.recipe

import com.squareup.moshi.Json

data class RecipeTaste(

    @Json(name = "fattiness")
    val fattiness: Int? = null,

    @Json(name = "spiciness")
    val spiciness: Double? = null,

    @Json(name = "saltiness")
    val saltiness: Double? = null,

    @Json(name = "bitterness")
    val bitterness: Double? = null,

    @Json(name = "savoriness")
    val savoriness: Double? = null,

    @Json(name = "sweetness")
    val sweetness: Double? = null,

    @Json(name = "sourness")
    val sourness: Double? = null
)
