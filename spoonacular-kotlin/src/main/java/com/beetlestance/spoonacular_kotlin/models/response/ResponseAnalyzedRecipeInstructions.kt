package com.beetlestance.spoonacular_kotlin.models.response

import com.squareup.moshi.Json

data class ResponseAnalyzedRecipeInstructions(

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
