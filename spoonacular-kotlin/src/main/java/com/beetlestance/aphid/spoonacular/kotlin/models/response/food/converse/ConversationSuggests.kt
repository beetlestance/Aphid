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
package com.beetlestance.aphid.spoonacular.kotlin.models.response.food.converse

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
