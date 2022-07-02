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
package com.beetlestance.aphid.data

import com.beetlestance.aphid.base.result.Failure
import com.beetlestance.aphid.base.result.Result
import com.beetlestance.aphid.base.result.Success
import com.beetlestance.aphid.spoonacular.kotlin.models.SpoonacularApiResponse
import com.beetlestance.aphid.spoonacular.kotlin.models.Success as SpoonacularSuccess

fun <T> SpoonacularApiResponse<T>.toResult(): Result<T> = try {
    when (this) {
        is SpoonacularSuccess -> {
            Success(data)
        }
        else -> {
            Failure(IllegalArgumentException())
        }
    }
} catch (e: Exception) {
    Failure(e)
}

suspend fun <T, E> SpoonacularApiResponse<T>.toResult(mapper: suspend (T) -> E): Result<E> = try {
    when (this) {
        is SpoonacularSuccess -> {
            Success(data = mapper(data))
        }
        else -> {
            Failure(IllegalArgumentException())
        }
    }
} catch (e: Exception) {
    Failure(e)
}
