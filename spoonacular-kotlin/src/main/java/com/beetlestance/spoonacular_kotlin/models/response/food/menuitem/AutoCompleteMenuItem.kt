package com.beetlestance.spoonacular_kotlin.models.response.food.menuitem

import com.squareup.moshi.Json

data class AutoCompleteMenuItem(

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
