package com.beetlestance.spoonacular_kotlin.models.response.food.product

import com.squareup.moshi.Json
import java.math.BigDecimal

data class ComparableProduct(

    @Json(name = "comparableProducts")
    val comparableProducts: ComparableProducts? = null
) {

    data class ComparableProducts(

        @Json(name = "price")
        val price: List<PriceItem?>? = null,

        @Json(name = "protein")
        val protein: List<ProteinItem?>? = null,

        @Json(name = "spoonacularScore")
        val spoonacularScore: List<SpoonacularScoreItem?>? = null,

        @Json(name = "calories")
        val calories: List<CaloriesItem?>? = null,

        @Json(name = "sugar")
        val sugar: List<SugarItem?>? = null,

        @Json(name = "likes")
        val likes: List<LikeItem?>? = null
    )

    data class PriceItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "difference")
        val difference: Int? = null,

        @Json(name = "id")
        val id: BigDecimal? = null,

        @Json(name = "title")
        val title: String? = null,
    )

    data class ProteinItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "difference")
        val difference: Int? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "title")
        val title: String? = null
    )

    data class SpoonacularScoreItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "difference")
        val difference: Int? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "title")
        val title: String? = null
    )

    data class CaloriesItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "difference")
        val difference: Int? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "title")
        val title: String? = null
    )

    data class SugarItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "difference")
        val difference: Int? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "title")
        val title: String? = null
    )

    data class LikeItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "difference")
        val difference: Int? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "title")
        val title: String? = null
    )
}
