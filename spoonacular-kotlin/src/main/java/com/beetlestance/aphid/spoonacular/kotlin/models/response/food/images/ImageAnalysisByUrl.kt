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
package com.beetlestance.aphid.spoonacular.kotlin.models.response.food.images

import com.squareup.moshi.Json

data class ImageAnalysisByUrl(

    @Json(name = "recipes")
    val recipes: List<RecipesItem?>? = null,

    @Json(name = "nutrition")
    val nutrition: Nutrition? = null,

    @Json(name = "category")
    val category: Category? = null
) {
    data class ConfidenceRange95Percent(

        @Json(name = "min")
        val min: Double? = null,

        @Json(name = "max")
        val max: Double? = null
    )

    data class Category(

        @Json(name = "probability")
        val probability: Double? = null,

        @Json(name = "name")
        val name: String? = null
    )

    data class Nutrition(

        @Json(name = "recipesUsed")
        val recipesUsed: Int? = null,

        @Json(name = "carbs")
        val carbs: Carbs? = null,

        @Json(name = "protein")
        val protein: Protein? = null,

        @Json(name = "fat")
        val fat: Fat? = null,

        @Json(name = "calories")
        val calories: Calories? = null
    )

    data class RecipesItem(

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "title")
        val title: String? = null,

        @Json(name = "imageType")
        val imageType: String? = null,

        @Json(name = "url")
        val url: String? = null
    )

    data class Carbs(

        @Json(name = "confidenceRange95Percent")
        val confidenceRange95Percent: ConfidenceRange95Percent? = null,

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "value")
        val value: Int? = null,

        @Json(name = "standardDeviation")
        val standardDeviation: Double? = null
    )

    data class Fat(

        @Json(name = "confidenceRange95Percent")
        val confidenceRange95Percent: ConfidenceRange95Percent? = null,

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "value")
        val value: Int? = null,

        @Json(name = "standardDeviation")
        val standardDeviation: Double? = null
    )

    data class Calories(

        @Json(name = "confidenceRange95Percent")
        val confidenceRange95Percent: ConfidenceRange95Percent? = null,

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "value")
        val value: Int? = null,

        @Json(name = "standardDeviation")
        val standardDeviation: Double? = null
    )

    data class Protein(

        @Json(name = "confidenceRange95Percent")
        val confidenceRange95Percent: ConfidenceRange95Percent? = null,

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "value")
        val value: Int? = null,

        @Json(name = "standardDeviation")
        val standardDeviation: Double? = null
    )
}
