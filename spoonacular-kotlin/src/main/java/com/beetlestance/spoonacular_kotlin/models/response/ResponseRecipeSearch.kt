package com.beetlestance.spoonacular_kotlin.models.response

import com.squareup.moshi.Json

data class ResponseRecipeSearch(

    @Json(name = "number")
    val number: Int? = null,

    @Json(name = "totalResults")
    val totalResults: Int? = null,

    @Json(name = "expires")
    val expires: Long? = null,

    @Json(name = "offset")
    val offset: Int? = null,

    @Json(name = "processingTimeMs")
    val processingTimeMs: Int? = null,

    @Json(name = "baseUri")
    val baseUri: String? = null,

    @Json(name = "isStale")
    val isStale: Boolean? = null,

    @Json(name = "results")
    val results: List<ResultsItem?>? = null
) {

    data class ResultsItem(

        @Json(name = "readyInMinutes")
        val readyInMinutes: Int? = null,

        @Json(name = "sourceUrl")
        val sourceUrl: String? = null,

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "servings")
        val servings: Int? = null,

        @Json(name = "openLicense")
        val openLicense: Int? = null,

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "title")
        val title: String? = null
    )
}
