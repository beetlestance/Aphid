/*
 * Copyright 2020 BeetleStance
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
