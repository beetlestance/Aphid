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
package com.beetlestance.spoonacular_kotlin.models.request.recipe

import com.squareup.moshi.Json

/**
 * @param ingredientList The ingredient list of the recipe, one ingredient per line.
 * @param servings The number of servings.
 * @param measure The original system of measurement, either "metric" or "us". (optional)
 * @param view How to visualize the ingredients, either "grid" or "list". (optional)
 * @param defaultCss Whether the default CSS should be added to the response. (optional)
 * @param showBacklink Whether to show a backlink to spoonacular. If set false, this call counts against your quota. (optional)
 * */

data class RequestVisualizeIngredients(

    @Json(name = "ingredientList")
    val ingredientList: String,

    @Json(name = "servings")
    val servings: Int,

    @Json(name = "measure")
    val measure: String?,

    @Json(name = "view")
    val view: String?,

    @Json(name = "defaultCss")
    val defaultCss: Boolean?,

    @Json(name = "showBacklink")
    val showBacklink: Boolean?

)
