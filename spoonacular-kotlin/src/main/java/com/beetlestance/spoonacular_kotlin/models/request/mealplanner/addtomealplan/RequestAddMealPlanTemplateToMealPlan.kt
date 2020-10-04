package com.beetlestance.spoonacular_kotlin.models.request.mealplanner.addtomealplan

import com.squareup.moshi.Json

/**
 * @param mealPlanTemplateId is unique id for meal plan template.
 * @param startDate is the timestamp of the day the item should be added to.
 * */
data class RequestAddMealPlanTemplateToMealPlan(

    @Json(name = "mealPlanTemplateId")
    val mealPlanTemplateId: Int,

    @Json(name = "startDate")
    val startDate: Long
) : RequestAddToMealPlan
