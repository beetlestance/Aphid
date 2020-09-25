package com.beetlestance.spoonacular_kotlin.models.response.food

import com.squareup.moshi.Json

data class FoodVideos(

    @Json(name = "totalResults")
    val totalResults: Int? = null,

    @Json(name = "videos")
    val videos: List<VideosItem?>? = null
) {

    data class VideosItem(

        @Json(name = "thumbnail")
        val thumbnail: String? = null,

        @Json(name = "youTubeId")
        val youTubeId: String? = null,

        @Json(name = "length")
        val length: Int? = null,

        @Json(name = "rating")
        val rating: Double? = null,

        @Json(name = "shortTitle")
        val shortTitle: String? = null,

        @Json(name = "title")
        val title: String? = null,

        @Json(name = "views")
        val views: Int? = null
    )
}
