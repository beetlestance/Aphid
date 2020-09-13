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

data class ResponseAnalyzeARecipeSearchQuery(

    @Json(name = "ingredients")
    val ingredients: List<IngredientsItem?>? = null,

    @Json(name = "dishes")
    val dishes: List<DishesItem?>? = null,

    @Json(name = "modifiers")
    val modifiers: List<Any?>? = null,

    @Json(name = "cuisines")
    val cuisines: List<Any?>? = null
) {

    data class DishesItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "name")
        val name: String? = null
    )

    data class IngredientsItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "include")
        val include: Boolean? = null,

        @Json(name = "name")
        val name: String? = null
    )
}
