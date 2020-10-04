package com.beetlestance.spoonacular_kotlin.models.request.mealplanner

import com.squareup.moshi.Json

/**
 * @param item desc of item for example "1 package baking powder"
 * @param aisle is optional and will be added if not given.
 * @param parse false if you want to put a non-food item to the shopping list.
 * */
data class RequestAddToShoppingList(

    @Json(name = "item")
    val item: String? = null,

    @Json(name = "parse")
    val parse: Boolean? = null,

    @Json(name = "aisle")
    val aisle: String? = null
)
