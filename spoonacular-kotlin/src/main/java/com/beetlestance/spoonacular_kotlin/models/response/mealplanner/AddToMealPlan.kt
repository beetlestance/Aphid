package com.beetlestance.spoonacular_kotlin.models.response.mealplanner

import com.squareup.moshi.Json

data class AddToMealPlan(

    @Json(name = "status")
    val status: String? = null,

    @Json(name = "id")
    val id: Long? = null

)

