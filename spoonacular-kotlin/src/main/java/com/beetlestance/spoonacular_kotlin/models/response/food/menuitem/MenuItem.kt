package com.beetlestance.spoonacular_kotlin.models.response.food.menuitem

import com.squareup.moshi.Json

data class MenuItem(

    @Json(name = "totalMenuItems")
    val totalMenuItems: Int? = null,

    @Json(name = "number")
    val number: Int? = null,

    @Json(name = "offset")
    val offset: Int? = null,

    @Json(name = "menuItems")
    val menuItems: List<MenuItemsItem?>? = null,

    @Json(name = "type")
    val type: String? = null
) {
    data class MenuItemsItem(

        @Json(name = "restaurantChain")
        val restaurantChain: String? = null,

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "readableServingSize")
        val readableServingSize: String? = null,

        @Json(name = "title")
        val title: String? = null,

        @Json(name = "imageType")
        val imageType: String? = null,

        @Json(name = "servingSize")
        val servingSize: String? = null
    )
}
