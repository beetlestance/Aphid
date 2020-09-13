package com.beetlestance.spoonacular_kotlin.models.response

import com.squareup.moshi.Json

data class ResponseGuessNutritionByDishName(

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
) {

    data class Protein(

        @Json(name = "confidenceRange95Percent")
        val confidenceRange95Percent: ConfidenceRange95Percent? = null,

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "value")
        val value: Double? = null,

        @Json(name = "standardDeviation")
        val standardDeviation: Double? = null
    )

    data class Carbs(

        @Json(name = "confidenceRange95Percent")
        val confidenceRange95Percent: ConfidenceRange95Percent? = null,

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "value")
        val value: Double? = null,

        @Json(name = "standardDeviation")
        val standardDeviation: Double? = null
    )

    data class Fat(

        @Json(name = "confidenceRange95Percent")
        val confidenceRange95Percent: ConfidenceRange95Percent? = null,

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "value")
        val value: Double? = null,

        @Json(name = "standardDeviation")
        val standardDeviation: Double? = null
    )

    data class Calories(

        @Json(name = "confidenceRange95Percent")
        val confidenceRange95Percent: ConfidenceRange95Percent? = null,

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "value")
        val value: Double? = null,

        @Json(name = "standardDeviation")
        val standardDeviation: Double? = null
    )

    data class ConfidenceRange95Percent(

        @Json(name = "min")
        val min: Double? = null,

        @Json(name = "max")
        val max: Double? = null
    )
}
