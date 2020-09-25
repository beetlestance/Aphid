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
package com.beetlestance.spoonacular_kotlin.models.response.food

import com.squareup.moshi.Json

data class FoodSiteContent(

    @Json(name = "Articles")
    val articles: List<ArticlesItem?>? = null,

    @Json(name = "Menu Items")
    val menuItems: List<MenuItemsItem?>? = null,

    @Json(name = "Recipes")
    val recipes: List<RecipesItem?>? = null,

    @Json(name = "Grocery Products")
    val groceryProducts: List<GroceryProductsItem?>? = null
) {

    data class DataPointsItem(

        @Json(name = "value")
        val value: String? = null,

        @Json(name = "key")
        val key: String? = null
    )

    data class ArticlesItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "link")
        val link: String? = null,

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "dataPoints")
        val dataPoints: List<Any?>? = null
    )

    data class MenuItemsItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "link")
        val link: String? = null,

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "dataPoints")
        val dataPoints: List<DataPointsItem?>? = null
    )

    data class RecipesItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "link")
        val link: String? = null,

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "dataPoints")
        val dataPoints: List<DataPointsItem?>? = null
    )

    data class GroceryProductsItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "link")
        val link: String? = null,

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "dataPoints")
        val dataPoints: List<DataPointsItem?>? = null
    )
}
