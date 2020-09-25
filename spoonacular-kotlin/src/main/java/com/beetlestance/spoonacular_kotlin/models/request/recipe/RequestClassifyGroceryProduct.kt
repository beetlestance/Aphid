package com.beetlestance.spoonacular_kotlin.models.request.recipe

import com.squareup.moshi.Json


/**
 *
 * @param title The title of product
 * @param upc The Universal Product Code
 * @param pluCode Price look-up codes
 */
data class RequestClassifyGroceryProduct(

    @Json(name = "title")
    val title: String,

    @Json(name = "upc")
    val upc: String,

    @Json(name = "plu_code")
    val pluCode: String

)
