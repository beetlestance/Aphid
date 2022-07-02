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
package com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe

import com.squareup.moshi.Json

data class ParseIngredients(

    @Json(name = "shoppingListUnits")
    val shoppingListUnits: List<String?>? = null,

    @Json(name = "image")
    val image: String? = null,

    @Json(name = "amount")
    val amount: Double? = null,

    @Json(name = "original")
    val original: String? = null,

    @Json(name = "unitLong")
    val unitLong: String? = null,

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
    val meta: List<Any?>? = null,

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
