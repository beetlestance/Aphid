package com.beetlestance.spoonacular_kotlin.models.response.food.ingredients

import com.squareup.moshi.Json

data class MapIngredientsToGroceryProducts(

    @Json(name = "originalName")
    val originalName: String? = null,

    @Json(name = "ingredientImage")
    val ingredientImage: String? = null,

    @Json(name = "original")
    val original: String? = null,

    @Json(name = "meta")
    val meta: List<String?>? = null,

    @Json(name = "products")
    val products: List<ProductsItem?>? = null
) {
    data class ProductsItem(

        @Json(name = "upc")
        val upc: String? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "title")
        val title: String? = null
    )
}
