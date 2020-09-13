package com.beetlestance.spoonacular_kotlin.models.response

import com.squareup.moshi.Json

data class ResponseRecipeNutrients(

    @Json(name = "bad")
    val bad: List<BadItem?>? = null,

    @Json(name = "carbs")
    val carbs: String? = null,

    @Json(name = "protein")
    val protein: String? = null,

    @Json(name = "fat")
    val fat: String? = null,

    @Json(name = "calories")
    val calories: String? = null,

    @Json(name = "good")
    val good: List<GoodItem?>? = null
) {
    data class GoodItem(

        @Json(name = "amount")
        val amount: String? = null,

        @Json(name = "percentOfDailyNeeds")
        val percentOfDailyNeeds: Double? = null,

        @Json(name = "indented")
        val indented: Boolean? = null,

        @Json(name = "title")
        val title: String? = null
    )

    data class BadItem(

        @Json(name = "amount")
        val amount: String? = null,

        @Json(name = "percentOfDailyNeeds")
        val percentOfDailyNeeds: Double? = null,

        @Json(name = "indented")
        val indented: Boolean? = null,

        @Json(name = "title")
        val title: String? = null
    )
}
