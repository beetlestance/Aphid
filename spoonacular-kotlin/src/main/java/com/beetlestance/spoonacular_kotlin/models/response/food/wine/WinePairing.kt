package com.beetlestance.spoonacular_kotlin.models.response.food.wine

import com.squareup.moshi.Json

data class WinePairing(

    @Json(name = "productMatches")
    val productMatches: List<ProductMatchesItem?>? = null,

    @Json(name = "pairingText")
    val pairingText: String? = null,

    @Json(name = "pairedWines")
    val pairedWines: List<String?>? = null
) {

    data class ProductMatchesItem(

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
        val description: Any? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "title")
        val title: String? = null,

        @Json(name = "ratingCount")
        val ratingCount: Double? = null
    )
}
