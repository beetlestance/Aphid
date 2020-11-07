package com.beetlestance.spoonacular_kotlin.utils

import com.squareup.moshi.Json

inline fun <reified T, reified R> T.serializedCopy(isNullSafe: Boolean = true): R? {

    val isMoshiSerialized = T::class.java.declaredFields.any {
        it.isAnnotationPresent(Json::class.java)
    }

    if (isMoshiSerialized.not()) throw IllegalArgumentException("Can only parse serialized class")
    val sourceAdapter = MoshiSerializer.moshi.adapter(T::class.java)
    if (isNullSafe) sourceAdapter.nullSafe()
    val sourceJson = sourceAdapter.toJson(this)

    val targetAdapter = MoshiSerializer.moshi.adapter(R::class.java)
    if (isNullSafe) targetAdapter.nullSafe()

    return targetAdapter.fromJson(sourceJson)
}

inline fun <reified T, reified R, C> T.serializedTransform(
    isNullSafe: Boolean = true,
    transform: (R) -> C
): C? {
    return transform(serializedCopy<T, R>(isNullSafe) ?: return null)
}

inline fun <reified T, reified R, C> T.serializedMapper(
    isNullSafe: Boolean = true,
    transform: (R) -> C
): List<C>? {
    val result = serializedCopy<T, R>(isNullSafe) ?: return null

    return if (result is List<*> && result.javaClass.componentType.isAssignableFrom(R::class.java))
        result.mapNotNull { data -> transform(data as R) }
    else throw IllegalArgumentException("Expected list found object")
}
