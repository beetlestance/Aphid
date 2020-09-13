package com.beetlestance.spoonacular_kotlin.models.request

import com.squareup.moshi.Json

/**
 *   @param instructions to be analyzed.
 */
data class RequestAnalyzeRecipeInstructions(

    @Json(name = "instructions")
    val instructions: String
)
