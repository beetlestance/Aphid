package com.beetlestance.spoonacular_kotlin.models.response.food.menuitem

import com.squareup.moshi.Json

data class MenuItemInformation(

    @Json(name = "images")
    val images: List<String?>? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "badges")
    val badges: List<String?>? = null,

    @Json(name = "numberOfServings")
    val numberOfServings: Double? = null,

    @Json(name = "generatedText")
    val generatedText: String? = null,

    @Json(name = "restaurantChain")
    val restaurantChain: String? = null,

    @Json(name = "nutrition")
    val nutrition: Nutrition? = null,

    @Json(name = "price")
    val price: Any? = null,

    @Json(name = "spoonacularScore")
    val spoonacularScore: Any? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "readableServingSize")
    val readableServingSize: String? = null,

    @Json(name = "servingSize")
    val servingSize: String? = null,

    @Json(name = "imageType")
    val imageType: String? = null,

    @Json(name = "breadcrumbs")
    val breadcrumbs: List<String?>? = null,

    @Json(name = "likes")
    val likes: Double? = null
) {
    data class Nutrition(

        @Json(name = "caloricBreakdown")
        val caloricBreakdown: CaloricBreakdown? = null,

        @Json(name = "carbs")
        val carbs: String? = null,

        @Json(name = "protein")
        val protein: String? = null,

        @Json(name = "fat")
        val fat: String? = null,

        @Json(name = "calories")
        val calories: Double? = null,

        @Json(name = "nutrients")
        val nutrients: List<NutrientsItem?>? = null
    )

    data class CaloricBreakdown(

        @Json(name = "percentCarbs")
        val percentCarbs: Double? = null,

        @Json(name = "percentProtein")
        val percentProtein: Double? = null,

        @Json(name = "percentFat")
        val percentFat: Double? = null
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
