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
package com.beetlestance.spoonacular_kotlin.models.response.recipe

import com.squareup.moshi.Json
import java.math.BigDecimal

data class SearchRecipeComplex(

    @Json(name = "number")
    val number: BigDecimal? = null,

    @Json(name = "totalResults")
    val totalResults: BigDecimal? = null,

    @Json(name = "offset")
    val offset: BigDecimal? = null,

    @Json(name = "results")
    val results: List<ResultsItem?>? = null
) {

    data class ResultsItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "carbs")
        val carbs: String? = null,

        @Json(name = "protein")
        val protein: String? = null,

        @Json(name = "fat")
        val fat: String? = null,

        @Json(name = "id")
        val id: BigDecimal? = null,

        @Json(name = "calories")
        val calories: BigDecimal? = null,

        @Json(name = "title")
        val title: String? = null,

        @Json(name = "imageType")
        val imageType: String? = null
    )
}
