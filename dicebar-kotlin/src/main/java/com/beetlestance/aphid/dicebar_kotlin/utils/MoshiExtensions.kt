package com.beetlestance.aphid.dicebar_kotlin.utils

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfig
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.internal.Util
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

typealias QUERY_PARAMS = Map<String, String>

val moshi: Moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

inline fun <reified T : DiceBarConfig> T.toQueryParams(): QUERY_PARAMS? {
    val sourceJson = moshi.toJson(this)
    return moshi.fromJson(sourceJson)
}

inline fun <reified T> Moshi.adapter(): JsonAdapter<T> {
    val type = object : TypeToken<T>() {}.type
    val adapterType = Types.newParameterizedType(T::class.java, type)
    val adapter: JsonAdapter<T> = adapter(adapterType)
    return adapter.nullSafe()
}

inline fun <reified T> Moshi.toJson(source: T): String {
    val adapter: JsonAdapter<T> = adapter()
    return adapter.toJson(source)
}

inline fun <reified T> Moshi.fromJson(source: String): T? {
    val adapter: JsonAdapter<T> = adapter()
    return adapter.fromJson(source)
}

open class TypeToken<T> {
    /**
     * Gets the generic super class type.
     */
    val type: Type = superClassTypeParameter()
    fun superClassTypeParameter(): Type {
        val superClass = javaClass.genericSuperclass
        if (superClass is Class<*>) {
            throw RuntimeException("Missing type parameter.")
        }
        // This is to get [T] from TypeToken
        val parameterType = (superClass as ParameterizedType).actualTypeArguments[0]
        val argumentType = if (parameterType is ParameterizedType) {
            // find argument type is [T] is parameterized
            parameterType.actualTypeArguments.first()
        } else parameterType
        return Util.canonicalize(argumentType)
    }
}