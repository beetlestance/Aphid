package com.beetlestance.spoonacular_kotlin.models.response

import com.squareup.moshi.Json

data class ResponseSearchRecipesByIngredients(

    @Json(name = "image")
    val image: String? = null,

    @Json(name = "usedIngredients")
    val usedIngredients: List<UsedIngredientsItem?>? = null,

    @Json(name = "missedIngredients")
    val missedIngredients: List<MissedIngredientsItem?>? = null,

    @Json(name = "missedIngredientCount")
    val missedIngredientCount: Int? = null,

    @Json(name = "unusedIngredients")
    val unusedIngredients: List<Any?>? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "imageType")
    val imageType: String? = null,

    @Json(name = "likes")
    val likes: Int? = null,

    @Json(name = "usedIngredientCount")
    val usedIngredientCount: Int? = null
) {

    data class MissedIngredientsItem(

        @Json(name = "originalName")
        val originalName: String? = null,

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "amount")
        val amount: Double? = null,

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "original")
        val original: String? = null,

        @Json(name = "unitShort")
        val unitShort: String? = null,

        @Json(name = "meta")
        val meta: List<Any?>? = null,

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "unitLong")
        val unitLong: String? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "aisle")
        val aisle: String? = null
    )

    data class UsedIngredientsItem(

        @Json(name = "originalName")
        val originalName: String? = null,

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "amount")
        val amount: Double? = null,

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "original")
        val original: String? = null,

        @Json(name = "unitShort")
        val unitShort: String? = null,

        @Json(name = "meta")
        val meta: List<Any?>? = null,

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "unitLong")
        val unitLong: String? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "aisle")
        val aisle: String? = null
    )
}
