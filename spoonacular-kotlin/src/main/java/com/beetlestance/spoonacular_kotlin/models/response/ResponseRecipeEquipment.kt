package com.beetlestance.spoonacular_kotlin.models.response

import com.squareup.moshi.Json

data class ResponseRecipeEquipment(

    @Json(name = "equipment")
    val equipment: List<EquipmentItem?>? = null
) {

    data class EquipmentItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "name")
        val name: String? = null
    )
}
