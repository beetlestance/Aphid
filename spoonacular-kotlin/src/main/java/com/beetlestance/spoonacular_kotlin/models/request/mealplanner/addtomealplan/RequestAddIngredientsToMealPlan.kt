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
package com.beetlestance.spoonacular_kotlin.models.request.mealplanner.addtomealplan

import com.beetlestance.spoonacular_kotlin.annotations.MealPlanType
import com.squareup.moshi.Json

/**
 *
 * Alternatively you can add all items of a meal plan template to a user's meal plan with one call
 * @see RequestAddMealPlanTemplateToMealPlan
 * @param date is the timestamp of the day the item should be added to.
 * @param slot is either 1, 2, or 3 for breakfast, lunch, or dinner respectively.
 * @param position is the order in the slot.
 * @param type is either INGREDIENTS for simple foods such as "1 banana"
 * @param value is information about the item and the structure depends on what type is set to.
 * */

data class RequestAddIngredientsToMealPlan(

    @Json(name = "date")
    val date: Long? = null,

    @Json(name = "slot")
    val slot: Int? = null,

    @Json(name = "position")
    val position: Int? = null,

    @Json(name = "type")
    @MealPlanType
    val type: String? = null,

    @Json(name = "value")
    val value: Value? = null

) : RequestAddToMealPlan {

    /**
     * @param ingredients used for adding simple foods with type INGREDIENTS.
     * */
    data class IngredientTypeValue(

        @Json(name = "ingredients")
        val ingredients: List<IngredientsItem?>? = null

    ) : Value

    /**
     * If the type is PRODUCT, MENU_ITEM, or RECIPE the value must contain the field servings and
     * the id of the respective item.
     * @param id the id of product, menu item or recipe.
     * @param servings The number of servings.
     * @param title the title of product, menu item or recipe.
     * @param image the image url of product, menu item or recipe.
     * */
    data class NonIngredientsTypeValue(

        @Json(name = "id")
        val id: Int? = null,

        @Json(name = "servings")
        val servings: Int? = null,

        @Json(name = "title")
        val title: String? = null,

        @Json(name = "image")
        val image: String? = null

    ) : Value

    /**
     * @param unit the unit for the given amount.
     * @param amount The amount of ingredient.
     * @param name the name of ingredient.
     * @param image the image url of product, menu item or recipe.
     * */
    data class IngredientsItem(

        @Json(name = "image")
        val image: String? = null,

        @Json(name = "unit")
        val unit: String? = null,

        @Json(name = "amount")
        val amount: String? = null,

        @Json(name = "name")
        val name: String? = null
    )
}
