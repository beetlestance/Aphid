package com.beetlestance.spoonacular_kotlin.models.response.food.converse

import com.squareup.moshi.Json

data class ConversationSuggests(

    @Json(name = "suggests")
    val suggests: Suggests? = null,

    @Json(name = "words")
    val words: List<String?>? = null

) {

    data class Suggests(

        @Json(name = "_")
        val suggestItem: List<SuggestItem?>? = null

    )

    data class SuggestItem(

        @Json(name = "name")
        val name: String? = null

    )
}
