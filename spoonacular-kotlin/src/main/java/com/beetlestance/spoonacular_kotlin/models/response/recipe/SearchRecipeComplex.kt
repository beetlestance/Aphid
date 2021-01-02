/*
 * Copyright 2021 BeetleStance
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

data class SearchRecipeComplex(

    @Json(name = "number")
    val number: Int? = null,

    @Json(name = "totalResults")
    val totalResults: Int? = null,

    @Json(name = "offset")
    val offset: Int? = null,

    @Json(name = "results")
    val results: List<ResultsItem>? = null
) {

    data class ResultsItem(

        @Json(name = "usedIngredients")
        val usedIngredients: List<UsedIngredientsItem?>? = null,

        @Json(name = "sustainable")
        val sustainable: Boolean? = null,

        @Json(name = "analyzedInstructions")
        val analyzedInstructions: List<AnalyzedRecipeInstructions?>? = null,

        @Json(name = "glutenFree")
        val glutenFree: Boolean? = null,

        @Json(name = "veryPopular")
        val veryPopular: Boolean? = null,

        @Json(name = "healthScore")
        val healthScore: Double? = null,

        @Json(name = "title")
        val title: String? = null,

        @Json(name = "diets")
        val diets: List<String?>? = null,

        @Json(name = "aggregateLikes")
        val aggregateLikes: Int? = null,

        @Json(name = "creditsText")
        val creditsText: String? = null,

        @Json(name = "readyInMinutes")
        val readyInMinutes: Int? = null,

        @Json(name = "sourceUrl")
        val sourceUrl: String? = null,

        @Json(name = "dairyFree")
        val dairyFree: Boolean? = null,

        @Json(name = "servings")
        val servings: Int? = null,

        @Json(name = "missedIngredients")
        val missedIngredients: List<MissedIngredientsItem?>? = null,

        @Json(name = "vegetarian")
        val vegetarian: Boolean? = null,

        @Json(name = "unusedIngredients")
        val unusedIngredients: List<Any?>? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "preparationMinutes")
        val preparationMinutes: Int? = null,

        @Json(name = "imageType")
        val imageType: String? = null,

        @Json(name = "likes")
        val likes: Int? = null,

        @Json(name = "summary")
        val summary: String? = null,

        @Json(name = "cookingMinutes")
        val cookingMinutes: Int? = null,

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "veryHealthy")
        val veryHealthy: Boolean? = null,

        @Json(name = "vegan")
        val vegan: Boolean? = null,

        @Json(name = "cheap")
        val cheap: Boolean? = null,

        @Json(name = "extendedIngredients")
        val extendedIngredients: List<ExtendedIngredientsItem?>? = null,

        @Json(name = "dishTypes")
        val dishTypes: List<String?>? = null,

        @Json(name = "gaps")
        val gaps: String? = null,

        @Json(name = "cuisines")
        val cuisines: List<String?>? = null,

        @Json(name = "usedIngredientCount")
        val usedIngredientCount: Int? = null,

        @Json(name = "lowFodmap")
        val lowFodmap: Boolean? = null,

        @Json(name = "nutrition")
        val nutrition: Nutrition? = null,

        @Json(name = "weightWatcherSmartPoints")
        val weightWatcherSmartPoints: Int? = null,

        @Json(name = "missedIngredientCount")
        val missedIngredientCount: Int? = null,

        @Json(name = "occasions")
        val occasions: List<String?>? = null,

        @Json(name = "spoonacularScore")
        val spoonacularScore: Double? = null,

        @Json(name = "pricePerServing")
        val pricePerServing: Double? = null,

        @Json(name = "sourceName")
        val sourceName: String? = null
    )

    data class UsedIngredientsItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "amount")
        val amount: Double? = null,

        @Json(name = "original")
        val original: String? = null,

        @Json(name = "extendedName")
        val extendedName: String? = null,

        @Json(name = "unitLong")
        val unitLong: String? = null,

        @Json(name = "aisle")
        val aisle: String? = null,

        @Json(name = "originalName")
        val originalName: String? = null,

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "unitShort")
        val unitShort: String? = null,

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
    )

    data class MissedIngredientsItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "amount")
        val amount: Double? = null,

        @Json(name = "original")
        val original: String? = null,

        @Json(name = "unitLong")
        val unitLong: String? = null,

        @Json(name = "aisle")
        val aisle: String? = null,

        @Json(name = "originalName")
        val originalName: String? = null,

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "unitShort")
        val unitShort: String? = null,

        @Json(name = "meta")
        val meta: List<String?>? = null,

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "originalString")
        val originalString: String? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "metaInformation")
        val metaInformation: List<String?>? = null,

        @Json(name = "extendedName")
        val extendedName: String? = null
    )

    data class Nutrition(

        @Json(name = "caloricBreakdown")
        val caloricBreakdown: CaloricBreakdown? = null,

        @Json(name = "weightPerServing")
        val weightPerServing: WeightPerServing? = null,

        @Json(name = "ingredients")
        val ingredients: List<IngredientsItem?>? = null,

        @Json(name = "flavanoids")
        val flavanoids: List<FlavanoidsItem?>? = null,

        @Json(name = "properties")
        val properties: List<PropertiesItem?>? = null,

        @Json(name = "nutrients")
        val nutrients: List<NutrientsItem?>? = null
    ) {

        data class CaloricBreakdown(

            @Json(name = "percentCarbs")
            val percentCarbs: Double? = null,

            @Json(name = "percentProtein")
            val percentProtein: Double? = null,

            @Json(name = "percentFat")
            val percentFat: Double? = null
        )

        data class IngredientsItem(

            @Json(name = "amount")
            val amount: Double? = null,

            @Json(name = "unit")
            val unit: String? = null,

            @Json(name = "name")
            val name: String? = null,

            @Json(name = "nutrients")
            val nutrients: List<NutrientsItem?>? = null
        )

        data class PropertiesItem(

            @Json(name = "amount")
            val amount: Double? = null,

            @Json(name = "unit")
            val unit: String? = null,

            @Json(name = "title")
            val title: String? = null
        )

        data class WeightPerServing(

            @Json(name = "amount")
            val amount: Int? = null,

            @Json(name = "unit")
            val unit: String? = null
        )

        data class FlavanoidsItem(

            @Json(name = "amount")
            val amount: Double? = null,

            @Json(name = "unit")
            val unit: String? = null,

            @Json(name = "title")
            val title: String? = null
        )

        data class NutrientsItem(

            @Json(name = "amount")
            val amount: Double? = null,

            @Json(name = "unit")
            val unit: String? = null,

            @Json(name = "percentOfDailyNeeds")
            val percentOfDailyNeeds: Double? = null,

            @Json(name = "title")
            val title: String? = null
        )
    }

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
