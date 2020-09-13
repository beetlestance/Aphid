package com.beetlestance.spoonacular_kotlin.models.request

import com.squareup.moshi.Json
import java.math.BigDecimal

/**
 * @param ingredientList The ingredient list of the recipe, one ingredient per line.
 * @param servings The number of servings.
 * @param mode The mode in which the widget should be delivered. 1 &#x3D; separate views (compact), 2 &#x3D; all in one view (full). (optional)
 * @param defaultCss Whether the default CSS should be added to the response. (optional)
 * @param showBacklink Whether to show a backlink to spoonacular. If set false, this call counts against your quota. (optional)
 * */

data class RequestVisualizePrceBreakdown(

    @Json(name = "ingredientList")
    val ingredientList: String,

    @Json(name = "servings")
    val servings: BigDecimal,

    @Json(name = "mode")
    val mode: BigDecimal?,

    @Json(name = "defaultCss")
    val defaultCss: Boolean?,

    @Json(name = "showBacklink")
    val showBacklink: Boolean?
)
