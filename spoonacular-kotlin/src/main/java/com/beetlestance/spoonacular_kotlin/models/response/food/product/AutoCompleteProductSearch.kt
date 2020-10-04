package com.beetlestance.spoonacular_kotlin.models.response.food.product

import com.squareup.moshi.Json

data class AutoCompleteProductSearch(

    @Json(name = "results")
    val results: List<ResultsItem?>? = null
) {

    data class ResultsItem(

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "title")
        val title: String? = null
    )
}
