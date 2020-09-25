package com.beetlestance.spoonacular_kotlin.models.response.food.wine

import com.squareup.moshi.Json

data class WineRecommendation(

    @Json(name = "recommendedWines")
    val recommendedWines: List<RecommendedWinesItem?>? = null,

    @Json(name = "totalFound")
    val totalFound: Int? = null
) {

    data class RecommendedWinesItem(

        @Json(name = "score")
        val score: Double? = null,

        @Json(name = "price")
        val price: String? = null,

        @Json(name = "averageRating")
        val averageRating: Double? = null,

        @Json(name = "imageUrl")
        val imageUrl: String? = null,

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
}
