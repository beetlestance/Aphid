package com.beetlestance.spoonacular_kotlin.models.request.food

import com.squareup.moshi.Json

/**
 * @param text The text in which food items, such as dish names and ingredients,
 * should be detected in.
 */
data class RequestDetectTextInFood(

    @Json(name = "text")
    val text: String
)
