package com.beetlestance.spoonacular_kotlin.models.response.mealplanner

import com.squareup.moshi.Json

data class PublicMealPlanTemplate(

    @Json(name = "templates")
    val templates: List<TemplatesItem?>? = null
) {

    data class TemplatesItem(

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "id")
        val id: Int? = null
    )
}
