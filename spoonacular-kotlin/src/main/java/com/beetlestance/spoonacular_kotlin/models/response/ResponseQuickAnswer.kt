package com.beetlestance.spoonacular_kotlin.models.response

import com.squareup.moshi.Json

data class ResponseQuickAnswer(

    @Json(name = "image")
    val image: String? = null,

    @Json(name = "answer")
    val answer: String? = null
)
