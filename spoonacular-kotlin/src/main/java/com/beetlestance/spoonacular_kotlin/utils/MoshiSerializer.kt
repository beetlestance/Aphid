package com.beetlestance.spoonacular_kotlin.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.*


class MoshiSerializer {

    companion object {
        const val ContentType: String = "Content-Type"
        const val Accept: String = "Accept"
        const val JsonMediaType: String = "application/json"
        const val FormDataMediaType: String = "multipart/form-data"
        const val FormUrlEncMediaType: String = "application/x-www-form-urlencoded"
        const val XmlMediaType: String = "application/xml"

        @JvmStatic
        val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .build()
    }

}
