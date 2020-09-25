package com.beetlestance.spoonacular_kotlin.models.response.food.converse

import com.squareup.moshi.Json

data class TalkToChatbot(

    @Json(name = "answerText")
    val answerText: String? = null,

    @Json(name = "media")
    val media: List<Any?>? = null
)
