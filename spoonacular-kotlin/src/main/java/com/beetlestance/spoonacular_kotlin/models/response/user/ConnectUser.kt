package com.beetlestance.spoonacular_kotlin.models.response.user

import com.squareup.moshi.Json

data class ConnectUser(

    @Json(name = "username")
    val username: String,

    @Json(name = "hash")
    val hash: String
)
