/*
 * Copyright 2020 BeetleStance
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
package com.beetlestance.spoonacular_kotlin.utils

import com.beetlestance.spoonacular_kotlin.models.ClientError
import com.beetlestance.spoonacular_kotlin.models.Informational
import com.beetlestance.spoonacular_kotlin.models.Redirection
import com.beetlestance.spoonacular_kotlin.models.ServerError
import com.beetlestance.spoonacular_kotlin.models.SpoonacularApiResponse
import com.beetlestance.spoonacular_kotlin.models.Success
import retrofit2.Response
import okhttp3.Response as OkHttpResponse

inline fun <reified T> Response<T>.toSpoonacularApiResponse(): SpoonacularApiResponse<T> {
    val okHttpResponse: OkHttpResponse = raw()
    return when {
        okHttpResponse.isRedirect -> Redirection(code(), headers().toMultimap())
        okHttpResponse.isInformational -> Informational(message(), code(), headers().toMultimap())
        okHttpResponse.isSuccessful -> Success(body() as T, code(), headers().toMultimap())
        okHttpResponse.isClientError -> ClientError(errorBody(), code(), headers().toMultimap())
        okHttpResponse.isServerError -> ServerError(
            null, errorBody(), code(), headers().toMultimap()
        )
        else -> throw IllegalStateException("Unknown error code ${code()}")
    }
}
