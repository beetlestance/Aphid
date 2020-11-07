package com.beetlestance.spoonacular_kotlin.utils

inline fun <T, reified R> T.serializedCopy(isNullSafe: Boolean = true): R? {

    val adapter = MoshiSerializer.moshi.adapter(R::class.java)
    if (isNullSafe) adapter.nullSafe()

    return adapter.fromJsonValue(this)
}

inline fun <T, reified R, C> T.serializedTransform(
    isNullSafe: Boolean = true,
    transform: (R) -> C
): C? {
    return transform(serializedCopy<T, R>(isNullSafe) ?: return null)
}

inline fun <T, reified R, C> Iterable<T>.serializedMapper(
    isNullSafe: Boolean = true,
    transform: (R) -> C
): List<C>? {
    return mapNotNull { data ->
        val result: R = data.serializedCopy(isNullSafe) ?: return@mapNotNull null
        transform(result)
    }
}
