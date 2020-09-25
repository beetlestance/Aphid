package com.beetlestance.spoonacular_kotlin.models.response.food.product

import com.squareup.moshi.Json

data class GroceryProduct(

    @Json(name = "number")
    val number: Int? = null,

    @Json(name = "offset")
    val offset: Int? = null,

    @Json(name = "totalProducts")
    val totalProducts: Int? = null,

    @Json(name = "type")
    val type: String? = null,

    @Json(name = "products")
    val products: List<ProductsItem?>? = null
) {
    data class ProductsItem(

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "title")
        val title: String? = null,

        @Json(name = "imageType")
        val imageType: String? = null
    )
}
