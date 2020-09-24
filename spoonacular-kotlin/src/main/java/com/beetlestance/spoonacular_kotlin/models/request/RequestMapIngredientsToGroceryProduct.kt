package com.beetlestance.spoonacular_kotlin.models.request

import com.squareup.moshi.Json

data class RequestMapIngredientsToGroceryProduct(

    @Json(name = "ingredients")
    val ingredients: List<String>,

    @Json(name = "servings")
    val servings: Int

)
