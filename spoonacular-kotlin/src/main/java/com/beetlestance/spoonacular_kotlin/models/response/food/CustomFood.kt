package com.beetlestance.spoonacular_kotlin.models.response.food

import com.squareup.moshi.Json

data class CustomFood(

    @Json(name = "number")
    val number: Int? = null,

    @Json(name = "offset")
    val offset: Int? = null,

    @Json(name = "type")
    val type: String? = null,

    @Json(name = "customFoods")
    val customFoods: List<CustomFoodsItem?>? = null

) {

    data class CustomFoodsItem(

        @Json(name = "servings")
        val servings: Int? = null,

        @Json(name = "price")
        val price: Double? = null,

        @Json(name = "imageUrl")
        val imageUrl: String? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "title")
        val title: String? = null

    )
}
