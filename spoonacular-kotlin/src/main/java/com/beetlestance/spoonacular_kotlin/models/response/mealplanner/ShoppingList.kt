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
