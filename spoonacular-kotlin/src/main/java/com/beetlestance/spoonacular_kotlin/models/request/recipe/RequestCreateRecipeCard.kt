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
import java.io.File
import java.math.BigDecimal

/**
 * @param title The title of the recipe.
 * @param image The binary image of the recipe as jpg.
 * @param ingredients The ingredient list of the recipe, one ingredient per line (separate lines with \n).
 * @param instructions The instructions to make the recipe. One step per line (separate lines with \n).
 * @param readyInMinutes The number of minutes it takes to get the recipe on the table.
 * @param servings The number of servings the recipe makes.
 * @param mask The mask to put over the recipe image ("ellipseMask", "diamondMask", "starMask", "heartMask", "potMask", "fishMask").
 * @param backgroundImage The background image ("none","background1", or "background2").
 * @param author The author of the recipe. (optional)
 * @param backgroundColor The background color for the recipe card as a hex-string. (optional)
 * @param fontColor The font color for the recipe card as a hex-string. (optional)
 * @param source The source of the recipe. (optional)
 * */

data class RequestCreateRecipeCard(

    @Json(name = "title")
    val title: String,

    @Json(name = "image")
    val image: File,

    @Json(name = "ingredients")
    val ingredients: String,

    @Json(name = "instructions")
    val instructions: String,

    @Json(name = "readyInMinutes")
    val readyInMinutes: BigDecimal,

    @Json(name = "servings")
    val servings: BigDecimal,

    @Json(name = "mask")
    val mask: String,

    @Json(name = "backgroundImage")
    val backgroundImage: String,

    @Json(name = "author")
    val author: String?,

    @Json(name = "backgroundColor")
    val backgroundColor: String?,

    @Json(name = "fontColor")
    val fontColor: String?,

    @Json(name = "source")
    val source: String?
)
