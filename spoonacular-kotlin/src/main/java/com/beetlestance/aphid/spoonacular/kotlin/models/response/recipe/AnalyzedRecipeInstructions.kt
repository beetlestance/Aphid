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
package com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe

import com.squareup.moshi.Json

data class AnalyzedRecipeInstructions(

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "steps")
    val steps: List<StepsItem?>? = null
) {

    data class StepsItem(

        @Json(name = "number")
        val number: Int? = null,

        @Json(name = "equipment")
        val equipment: List<EquipmentItem?>? = null,

        @Json(name = "ingredients")
        val ingredients: List<IngredientsItem?>? = null,

        @Json(name = "step")
        val step: String? = null,

        @Json(name = "length")
        val length: Length? = null
    )

    data class EquipmentItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "temperature")
        val temperature: Temperature? = null,

        @Json(name = "id")
        val id: Int? = null
    )

    data class IngredientsItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "id")
        val id: Int? = null
    )

    data class Temperature(

        @Json(name = "number")
        val number: Double? = null,

        @Json(name = "unit")
        val unit: String? = null
    )

    data class Length(

        @Json(name = "number")
        val number: Int? = null,

        @Json(name = "unit")
        val unit: String? = null
    )
}
