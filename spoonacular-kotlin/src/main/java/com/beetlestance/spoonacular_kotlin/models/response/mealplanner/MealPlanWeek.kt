package com.beetlestance.spoonacular_kotlin.models.response.mealplanner

import com.squareup.moshi.Json

data class MealPlanWeek(

    @Json(name = "days")
    val days: List<DaysItem?>? = null
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

    data class DaysItem(

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
    )

    data class NutritionSummaryDinner(

        @Json(name = "nutrients")
        val nutrients: List<NutrientsItem?>? = null
    )

    data class IngredientsItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "amount")
        val amount: String? = null,

        @Json(name = "name")
        val name: String? = null
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
