/*
 * Copyright 2021 BeetleStance
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beetlestance.aphid.spoonacular.kotlin.utils

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
