package com.beetlestance.spoonacular_kotlin.models.response

import com.squareup.moshi.Json

data class ResponseAnalyzeARecipeSearchQuery(

    @Json(name = "ingredients")
    val ingredients: List<IngredientsItem?>? = null,

    @Json(name = "dishes")
    val dishes: List<DishesItem?>? = null,

    @Json(name = "modifiers")
    val modifiers: List<Any?>? = null,

    @Json(name = "cuisines")
    val cuisines: List<Any?>? = null
) {

    data class DishesItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "name")
        val name: String? = null
    )

    data class IngredientsItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "include")
        val include: Boolean? = null,

        @Json(name = "name")
        val name: String? = null
    )
}
