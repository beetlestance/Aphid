package com.beetlestance.spoonacular_kotlin.models.response.mealplanner

import com.squareup.moshi.Json

data class ShoppingList(

    @Json(name = "cost")
    val cost: Double? = null,

    @Json(name = "endDate")
    val endDate: Int? = null,

    @Json(name = "aisles")
    val aisles: List<AislesItem?>? = null,

    @Json(name = "startDate")
    val startDate: Int? = null
) {

    data class AislesItem(

        @Json(name = "aisle")
        val aisle: String? = null,

        @Json(name = "items")
        val items: List<ItemsItem?>? = null
    )

    data class ItemsItem(

        @Json(name = "ingredientId")
        val ingredientId: Int? = null,

        @Json(name = "measures")
        val measures: Measures? = null,

        @Json(name = "cost")
        val cost: Double? = null,

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "pantryItem")
        val pantryItem: Boolean? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "aisle")
        val aisle: String? = null
    )

    data class Measures(

        @Json(name = "original")
        val original: Original? = null,

        @Json(name = "metric")
        val metric: Metric? = null,

        @Json(name = "us")
        val us: Us? = null
    )

    data class Us(

        @Json(name = "amount")
        val amount: Double? = null,

        @Json(name = "unit")
        val unit: String? = null
    )

    data class Original(

        @Json(name = "amount")
        val amount: Double? = null,

        @Json(name = "unit")
        val unit: String? = null
    )

    data class Metric(

        @Json(name = "amount")
        val amount: Double? = null,

        @Json(name = "unit")
        val unit: String? = null
    )
}
