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
package com.beetlestance.spoonacular_kotlin.models.response

import com.squareup.moshi.Json

data class ResponseRecipeIngredients(

    @Json(name = "ingredients")
    val ingredients: List<IngredientsItem?>? = null
) {

    data class IngredientsItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "amount")
        val amount: Amount? = null,

        @Json(name = "name")
        val name: String? = null
    )

    data class Us(

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "value")
        val value: Double? = null
    )

    data class Amount(

        @Json(name = "metric")
        val metric: Metric? = null,

        @Json(name = "us")
        val us: Us? = null
    )

    data class Metric(

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "value")
        val value: Double? = null
    )
}
