package com.beetlestance.aphid.dicebar_kotlin.utils

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

typealias QUERY_PARAMS = Map<String, String>

private val moshi: Moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

fun DiceBarConfig.toQueryParams(): QUERY_PARAMS? {
    val sourceAdapter = moshi.adapter(DiceBarConfig::class.java)
    val sourceJson = sourceAdapter.toJson(this)

    val targetAdapter = moshi.adapter<QUERY_PARAMS>(Map::class.java)
    return targetAdapter.fromJson(sourceJson)
}
