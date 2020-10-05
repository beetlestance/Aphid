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

data class MealPlanDay(

    @Json(name = "nutritionSummaryDinner")
    val nutritionSummaryDinner: NutritionSummaryDinner? = null,

    @Json(name = "date")
    val date: Int? = null,

    @Json(name = "nutritionSummary")
    val nutritionSummary: NutritionSummary? = null,

    @Json(name = "nutritionSummaryLunch")
    val nutritionSummaryLunch: NutritionSummaryLunch? = null,

    @Json(name = "day")
    val day: String? = null,

    @Json(name = "items")
    val items: List<ItemsItem?>? = null,

    @Json(name = "nutritionSummaryBreakfast")
    val nutritionSummaryBreakfast: NutritionSummaryBreakfast? = null
) {

    data class NutrientsItem(

        @Json(name = "amount")
        val amount: Int? = null,

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "percentDailyNeeds")
        val percentDailyNeeds: Int? = null,

        @Json(name = "title")
        val title: String? = null
    )

    data class NutritionSummary(

        @Json(name = "nutrients")
        val nutrients: List<NutrientsItem?>? = null
    )

    data class NutritionSummaryLunch(

        @Json(name = "nutrients")
        val nutrients: List<NutrientsItem?>? = null
    )

    data class NutritionSummaryDinner(

        @Json(name = "nutrients")
        val nutrients: List<NutrientsItem?>? = null
    )

    data class Value(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "servings")
        val servings: Int? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "title")
        val title: String? = null,

        @Json(name = "imageType")
        val imageType: String? = null
    )

    data class NutritionSummaryBreakfast(

        @Json(name = "nutrients")
        val nutrients: List<NutrientsItem?>? = null
    )

    data class ItemsItem(

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "slot")
        val slot: Int? = null,

        @Json(name = "position")
        val position: Int? = null,

        @Json(name = "type")
        val type: String? = null,

        @Json(name = "value")
        val value: Value? = null
    )
}
