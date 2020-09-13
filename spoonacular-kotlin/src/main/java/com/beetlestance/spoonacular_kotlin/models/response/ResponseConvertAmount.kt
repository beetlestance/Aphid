package com.beetlestance.spoonacular_kotlin.models.response

import com.squareup.moshi.Json

data class ResponseConvertAmount(

    @Json(name = "targetUnit")
    val targetUnit: String? = null,

    @Json(name = "answer")
    val answer: String? = null,

    @Json(name = "targetAmount")
    val targetAmount: Double? = null,

    @Json(name = "sourceAmount")
    val sourceAmount: Double? = null,

    @Json(name = "sourceUnit")
    val sourceUnit: String? = null
)
