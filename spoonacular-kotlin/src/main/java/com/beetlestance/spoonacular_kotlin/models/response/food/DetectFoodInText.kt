package com.beetlestance.spoonacular_kotlin.models.response.food

import com.squareup.moshi.Json

data class DetectFoodInText(

    @Json(name = "annotations")
    val annotations: List<AnnotationsItem?>? = null
) {
    data class AnnotationsItem(

        @Json(name = "annotation")
        val annotation: String? = null,

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "tag")
        val tag: String? = null
    )
}
