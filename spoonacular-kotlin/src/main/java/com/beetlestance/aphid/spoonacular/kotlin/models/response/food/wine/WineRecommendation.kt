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
package com.beetlestance.aphid.spoonacular.kotlin.models.response.food.wine

import com.squareup.moshi.Json

data class WineRecommendation(

    @Json(name = "recommendedWines")
    val recommendedWines: List<RecommendedWinesItem?>? = null,

    @Json(name = "totalFound")
    val totalFound: Int? = null
) {

    data class RecommendedWinesItem(

        @Json(name = "score")
        val score: Double? = null,

        @Json(name = "price")
        val price: String? = null,

        @Json(name = "averageRating")
        val averageRating: Double? = null,

        @Json(name = "imageUrl")
        val imageUrl: String? = null,

        @Json(name = "link")
        val link: String? = null,

        @Json(name = "description")
        val description: String? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "title")
        val title: String? = null,

        @Json(name = "ratingCount")
        val ratingCount: Double? = null
    )
}
