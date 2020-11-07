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

data class RecipeInformation(

    @Json(name = "instructions")
    val instructions: String? = null,

    @Json(name = "sustainable")
    val sustainable: Boolean? = null,

    @Json(name = "analyzedInstructions")
    val analyzedInstructions: List<AnalyzedRecipeInstructions?>? = null,

    @Json(name = "glutenFree")
    val glutenFree: Boolean? = null,

    @Json(name = "veryPopular")
    val veryPopular: Boolean? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "healthScore")
    val healthScore: Double? = null,

    @Json(name = "diets")
    val diets: List<String?>? = null,

    @Json(name = "aggregateLikes")
    val aggregateLikes: Int? = null,

    @Json(name = "readyInMinutes")
    val readyInMinutes: Int? = null,

    @Json(name = "sourceUrl")
    val sourceUrl: String? = null,

    @Json(name = "creditsText")
    val creditsText: String? = null,

    @Json(name = "dairyFree")
    val dairyFree: Boolean? = null,

    @Json(name = "servings")
    val servings: Int? = null,

    @Json(name = "vegetarian")
    val vegetarian: Boolean? = null,

    @Json(name = "whole30")
    val whole30: Boolean? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "imageType")
    val imageType: String? = null,

    @Json(name = "winePairing")
    val winePairing: WinePairing? = null,

    @Json(name = "summary")
    val summary: String? = null,

    @Json(name = "image")
    val image: String? = null,

    @Json(name = "veryHealthy")
    val veryHealthy: Boolean? = null,

    @Json(name = "vegan")
    val vegan: Boolean? = null,

    @Json(name = "cheap")
    val cheap: Boolean? = null,

    @Json(name = "dishTypes")
    val dishTypes: List<String?>? = null,

    @Json(name = "extendedIngredients")
    val extendedIngredients: List<ExtendedIngredientsItem?>? = null,

    @Json(name = "gaps")
    val gaps: String? = null,

    @Json(name = "cuisines")
    val cuisines: List<String?>? = null,

    @Json(name = "lowFodmap")
    val lowFodmap: Boolean? = null,

    @Json(name = "license")
    val license: String? = null,

    @Json(name = "weightWatcherSmartPoints")
    val weightWatcherSmartPoints: Int? = null,

    @Json(name = "occasions")
    val occasions: List<String?>? = null,

    @Json(name = "spoonacularScore")
    val spoonacularScore: Double? = null,

    @Json(name = "pricePerServing")
    val pricePerServing: Double? = null,

    @Json(name = "sourceName")
    val sourceName: String? = null,

    @Json(name = "spoonacularSourceUrl")
    val spoonacularSourceUrl: String? = null,

    @Json(name = "ketogenic")
    val ketogenic: Boolean? = null
) {

    data class WinePairing(

        @Json(name = "productMatches")
        val productMatches: List<ProductMatchesItem?>? = null,

        @Json(name = "pairingText")
        val pairingText: String? = null,

        @Json(name = "pairedWines")
        val pairedWines: List<String?>? = null
    )

    data class ProductMatchesItem(

        @Json(name = "score")
        val score: Double? = null,

        @Json(name = "price")
        val price: String? = null,

        @Json(name = "imageUrl")
        val imageUrl: String? = null,

        @Json(name = "averageRating")
        val averageRating: Double? = null,

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

    data class ExtendedIngredientsItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "amount")
        val amount: Double? = null,

        @Json(name = "original")
        val original: String? = null,

        @Json(name = "aisle")
        val aisle: String? = null,

        @Json(name = "consistency")
        val consistency: String? = null,

        @Json(name = "originalName")
        val originalName: String? = null,

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "measures")
        val measures: Measures? = null,

        @Json(name = "meta")
        val meta: List<String?>? = null,

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "originalString")
        val originalString: String? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "metaInformation")
        val metaInformation: List<String?>? = null
    ) {
        data class Measures(

            @Json(name = "metric")
            val metric: Metric? = null,

            @Json(name = "us")
            val us: Us? = null
        )

        data class Metric(

            @Json(name = "amount")
            val amount: Double? = null,

            @Json(name = "unitShort")
            val unitShort: String? = null,

            @Json(name = "unitLong")
            val unitLong: String? = null
        )


        data class Us(

            @Json(name = "amount")
            val amount: Double? = null,

            @Json(name = "unitShort")
            val unitShort: String? = null,

            @Json(name = "unitLong")
            val unitLong: String? = null
        )
    }
}
