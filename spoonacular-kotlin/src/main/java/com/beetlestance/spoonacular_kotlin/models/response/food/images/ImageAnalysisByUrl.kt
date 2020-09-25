package com.beetlestance.spoonacular_kotlin.models.response.food.images

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
