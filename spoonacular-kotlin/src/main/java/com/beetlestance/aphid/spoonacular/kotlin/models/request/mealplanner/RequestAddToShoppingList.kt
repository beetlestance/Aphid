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
package com.beetlestance.aphid.spoonacular.kotlin.models.request.mealplanner

import com.squareup.moshi.Json

/**
 * @param item desc of item for example "1 package baking powder"
 * @param aisle is optional and will be added if not given.
 * @param parse false if you want to put a non-food item to the shopping list.
 * */
data class RequestAddToShoppingList(

    @Json(name = "item")
    val item: String? = null,

    @Json(name = "parse")
    val parse: Boolean? = null,

    @Json(name = "aisle")
    val aisle: String? = null
)
