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
package com.beetlestance.spoonacular_kotlin.models.request.recipe

import com.squareup.moshi.Json

/**
 *
 * @param title The title of product
 * @param upc The Universal Product Code
 * @param pluCode Price look-up codes
 */
data class RequestClassifyGroceryProduct(

    @Json(name = "title")
    val title: String,

    @Json(name = "upc")
    val upc: String,

    @Json(name = "plu_code")
    val pluCode: String

)
