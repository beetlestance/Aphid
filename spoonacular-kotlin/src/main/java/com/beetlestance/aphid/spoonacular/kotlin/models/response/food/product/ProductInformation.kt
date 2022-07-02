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
package com.beetlestance.aphid.spoonacular.kotlin.models.response.food.product

import com.squareup.moshi.Json

data class ProductInformation(

    @Json(name = "ingredientCount")
    val ingredientCount: Int? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "aisle")
    val aisle: String? = null,

    @Json(name = "badges")
    val badges: List<String?>? = null,

    @Json(name = "generatedText")
    val generatedText: String? = null,

    @Json(name = "nutrition")
    val nutrition: Nutrition? = null,

    @Json(name = "servings")
    val servings: Servings? = null,

    @Json(name = "price")
    val price: Double? = null,

    @Json(name = "ingredientList")
    val ingredientList: String? = null,

    @Json(name = "ingredients")
    val ingredients: List<IngredientsItem?>? = null,

    @Json(name = "spoonacularScore")
    val spoonacularScore: Double? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "imageType")
    val imageType: String? = null,

    @Json(name = "breadcrumbs")
    val breadcrumbs: List<String?>? = null,

    @Json(name = "importantBadges")
    val importantBadges: List<String?>? = null,

    @Json(name = "likes")
    val likes: Int? = null
) {
    data class CaloricBreakdown(

        @Json(name = "percentCarbs")
        val percentCarbs: Double? = null,

        @Json(name = "percentProtein")
        val percentProtein: Double? = null,

        @Json(name = "percentFat")
        val percentFat: Int? = null
    )

    data class Servings(

        @Json(name = "number")
        val number: Int? = null,

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "size")
        val size: Int? = null
    )

    data class NutrientsItem(

        @Json(name = "amount")
        val amount: Int? = null,

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "percentOfDailyNeeds")
        val percentOfDailyNeeds: Double? = null,

        @Json(name = "title")
        val title: String? = null
    )

    data class IngredientsItem(

        @Json(name = "safety_level")
        val safetyLevel: String? = null,

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "description")
        val description: String? = null
    )

    data class Nutrition(

        @Json(name = "caloricBreakdown")
        val caloricBreakdown: CaloricBreakdown? = null,

        @Json(name = "nutrients")
        val nutrients: List<NutrientsItem?>? = null
    )
}
