package com.beetlestance.spoonacular_kotlin.utils

import com.squareup.moshi.Types
import java.lang.reflect.Type

inline fun <reified T, reified R> T.serializedCopy(isNullSafe: Boolean = true): R? {
    val sourceAdapter = MoshiSerializer.moshi.adapter(T::class.java)
    if (isNullSafe) sourceAdapter.nullSafe()
    val sourceJson = sourceAdapter.toJson(this)
    val targetAdapter = MoshiSerializer.moshi.adapter(R::class.java)
    if (isNullSafe) targetAdapter.nullSafe()
    return targetAdapter.fromJson(sourceJson)
}

inline fun <reified T, reified R> List<T>.serializedCopy(isNullSafe: Boolean = true): List<R>? {
    val sourceType: Type = Types.newParameterizedType(List::class.java, T::class.java)
    val sourceAdapter = MoshiSerializer.moshi.adapter<List<T>>(sourceType)
    if (isNullSafe) sourceAdapter.nullSafe()
    val sourceJson = sourceAdapter.toJson(this)

    val targetType: Type = Types.newParameterizedType(List::class.java, R::class.java)
    val targetAdapter = MoshiSerializer.moshi.adapter<List<R>>(targetType)
    if (isNullSafe) targetAdapter.nullSafe()
    return targetAdapter.fromJson(sourceJson)
}

inline fun <reified T, reified R, C> T.serializedTransform(
    isNullSafe: Boolean = true,
    transform: (R) -> C
): C? {
    return transform(serializedCopy<T, R>(isNullSafe) ?: return null)
}

inline fun <reified T, C, reified R> Iterable<T>.serializedMapper(
    isNullSafe: Boolean = true,
    transform: (R) -> C
): List<C>? {
    return mapNotNull { data ->
        val result: R = data.serializedCopy(isNullSafe) ?: return@mapNotNull null
        transform(result)
    }
}
