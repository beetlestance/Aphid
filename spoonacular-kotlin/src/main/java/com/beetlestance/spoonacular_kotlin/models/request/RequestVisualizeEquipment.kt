package com.beetlestance.spoonacular_kotlin.models.request

import com.squareup.moshi.Json
import java.math.BigDecimal

/**
 * @param ingredientList The ingredient list of the recipe, one ingredient per line.
 * @param servings The number of servings.
 * @param view How to visualize the equipment, either "grid" or "list". (optional)
 * @param defaultCss Whether the default CSS should be added to the response. (optional)
 * @param showBacklink Whether to show a backlink to spoonacular. If set false, this call counts against your quota. (optional)
 */
data class RequestVisualizeEquipment(

    @Json(name = "ingredientList")
    val ingredientList: String,

    @Json(name = "servings")
    val servings: BigDecimal,

    @Json(name = "view")
    val view: String?,

    @Json(name = "defaultCss")
    val defaultCss: Boolean?,

    @Json(name = "showBacklink")
    val showBacklink: Boolean?
)
