/*
 * Copyright 2020 BeetleStance
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beetlestance.aphid.spoonacular.kotlin.models.response.food

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
