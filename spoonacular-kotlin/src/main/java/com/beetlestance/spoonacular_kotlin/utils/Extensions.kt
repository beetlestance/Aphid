package com.beetlestance.spoonacular_kotlin.utils

inline fun <reified T, reified R> T.serializedCopy(isNullSafe: Boolean = true): R? {

    val adapter = MoshiSerializer.moshi.adapter(R::class.java)
    if (isNullSafe) adapter.nullSafe()

    return adapter.fromJsonValue(this)
}

inline fun <reified T, reified R, C> T.serializedTransform(
    isNullSafe: Boolean = true,
    transform: (R) -> C
): C? {
    return transform(serializedCopy<T, R>(isNullSafe) ?: return null)
}

inline fun <reified T : Collection<T>, reified R : Collection<R>, C> T.serializedMapper(
    isNullSafe: Boolean = true,
    transform: (R) -> C
): List<C>? {
    val result = serializedCopy<T, R>(isNullSafe) ?: return null
    return result.mapNotNull { data -> transform(data) }
}
