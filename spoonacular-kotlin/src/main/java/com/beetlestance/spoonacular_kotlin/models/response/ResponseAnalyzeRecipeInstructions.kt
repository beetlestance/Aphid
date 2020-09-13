package com.beetlestance.spoonacular_kotlin.models.response

import com.squareup.moshi.Json

data class ResponseAnalyzeRecipeInstructions(

    @Json(name = "parsedInstructions")
    val parsedInstructions: List<ParsedInstructionsItem?>? = null,

    @Json(name = "ingredients")
    val ingredients: List<IngredientsItem?>? = null,

    @Json(name = "equipment")
    val equipment: List<EquipmentItem?>? = null
) {

    data class EquipmentItem(

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "id")
        val id: Int? = null
    )

    data class StepsItem(

        @Json(name = "number")
        val number: Int? = null,

        @Json(name = "ingredients")
        val ingredients: List<IngredientsItem?>? = null,

        @Json(name = "equipment")
        val equipment: List<EquipmentItem?>? = null,

        @Json(name = "step")
        val step: String? = null
    )

    data class IngredientsItem(

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "id")
        val id: Int? = null
    )

    data class ParsedInstructionsItem(

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "steps")
        val steps: List<StepsItem?>? = null
    )

}
