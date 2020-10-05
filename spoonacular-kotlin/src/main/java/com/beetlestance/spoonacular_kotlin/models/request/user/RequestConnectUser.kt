package com.beetlestance.spoonacular_kotlin.models.request.user

import com.squareup.moshi.Json

data class RequestConnectUser(

    @Json(name = "firstName")
    val firstName: String? = null,

    @Json(name = "lastName")
    val lastName: String? = null,

    @Json(name = "email")
    val email: String? = null,

    @Json(name = "username")
    val username: String? = null
)
