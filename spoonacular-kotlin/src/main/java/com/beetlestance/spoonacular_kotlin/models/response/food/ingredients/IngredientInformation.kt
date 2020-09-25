package com.beetlestance.spoonacular_kotlin.models.response.food.ingredients

import com.squareup.moshi.Json

data class IngredientInformation(

    @Json(name = "shoppingListUnits")
    val shoppingListUnits: List<String?>? = null,

    @Json(name = "image")
    val image: String? = null,

    @Json(name = "amount")
    val amount: Double? = null,

    @Json(name = "original")
    val original: String? = null,

    @Json(name = "categoryPath")
    val categoryPath: List<String?>? = null,

    @Json(name = "unitLong")
    val unitLong: String? = null,

    @Json(name = "possibleUnits")
    val possibleUnits: List<String?>? = null,

    @Json(name = "estimatedCost")
    val estimatedCost: EstimatedCost? = null,

    @Json(name = "aisle")
    val aisle: String? = null,

    @Json(name = "consistency")
    val consistency: String? = null,

    @Json(name = "originalName")
    val originalName: String? = null,

    @Json(name = "unit")
    val unit: String? = null,

    @Json(name = "unitShort")
    val unitShort: String? = null,

    @Json(name = "meta")
    val meta: List<String?>? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "id")
    val id: Int? = null
) {

    data class EstimatedCost(

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "value")
        val value: Double? = null
    )
}
