package com.beetlestance.spoonacular_kotlin.utils

import com.squareup.moshi.internal.Util
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


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