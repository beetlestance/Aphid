package com.beetlestance.spoonacular_kotlin.models.response.mealplanner

import com.squareup.moshi.Json

data class GenerateMealPlan(

    @Json(name = "meals")
    val meals: List<MealsItem?>? = null,

    @Json(name = "nutrients")
    val nutrients: Nutrients? = null,

    @Json(name = "week")
    val week: Week? = null
) {

    data class MealsItem(

        @Json(name = "readyInMinutes")
        val readyInMinutes: Int? = null,

        @Json(name = "sourceUrl")
        val sourceUrl: String? = null,

        @Json(name = "servings")
        val servings: Int? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "title")
        val title: String? = null,

        @Json(name = "imageType")
        val imageType: String? = null
    )

    data class Nutrients(

        @Json(name = "carbohydrates")
        val carbohydrates: Double? = null,

        @Json(name = "protein")
        val protein: Double? = null,

        @Json(name = "fat")
        val fat: Double? = null,

        @Json(name = "calories")
        val calories: Double? = null
    )

    data class Week(

        @Json(name = "sunday")
        val sunday: Sunday? = null,


        @Json(name = "monday")
        val monday: Monday? = null,

        @Json(name = "tuesday")
        val tuesday: Tuesday? = null,

        @Json(name = "wednesday")
        val wednesday: Wednesday? = null,

        @Json(name = "thursday")
        val thursday: Thursday? = null,

        @Json(name = "friday")
        val friday: Friday? = null,

        @Json(name = "saturday")
        val saturday: Saturday? = null,
    )

    data class Sunday(

        @Json(name = "meals")
        val meals: List<MealsItem?>? = null,

        @Json(name = "nutrients")
        val nutrients: Nutrients? = null
    )

    data class Monday(

        @Json(name = "meals")
        val meals: List<MealsItem?>? = null,

        @Json(name = "nutrients")
        val nutrients: Nutrients? = null
    )

    data class Tuesday(

        @Json(name = "meals")
        val meals: List<MealsItem?>? = null,

        @Json(name = "nutrients")
        val nutrients: Nutrients? = null
    )

    data class Wednesday(

        @Json(name = "meals")
        val meals: List<MealsItem?>? = null,

        @Json(name = "nutrients")
        val nutrients: Nutrients? = null
    )

    data class Thursday(

        @Json(name = "meals")
        val meals: List<MealsItem?>? = null,

        @Json(name = "nutrients")
        val nutrients: Nutrients? = null
    )

    data class Friday(

        @Json(name = "meals")
        val meals: List<MealsItem?>? = null,

        @Json(name = "nutrients")
        val nutrients: Nutrients? = null
    )

    data class Saturday(

        @Json(name = "meals")
        val meals: List<MealsItem?>? = null,

        @Json(name = "nutrients")
        val nutrients: Nutrients? = null
    )
}
