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
@file:Suppress("unused")

package com.beetlestance.spoonacular_kotlin.utils

import com.beetlestance.spoonacular_kotlin.models.ClientError
import com.beetlestance.spoonacular_kotlin.models.Informational
import com.beetlestance.spoonacular_kotlin.models.Redirection
import com.beetlestance.spoonacular_kotlin.models.ServerError
import com.beetlestance.spoonacular_kotlin.models.SpoonacularApiResponse
import com.beetlestance.spoonacular_kotlin.models.Success
import okhttp3.Response

/**
 * Provides an extension to evaluation whether the response is a 1xx code
 */
val Response.isInformational: Boolean get() = this.code in 100..199

/**
 * Provides an extension to evaluation whether the response is a 4xx code
 */
val Response.isClientError: Boolean get() = this.code in 400..499

/**
 * Provides an extension to evaluation whether the response is a 5xx (Standard) through 999 (non-standard) code
 */
val Response.isServerError: Boolean get() = this.code in 500..999

inline fun <reified T> Response.toSpoonacularApiResponse(): SpoonacularApiResponse<T> {

    return when {
        isRedirect -> Redirection(code, headers.toMultimap())
        isInformational -> Informational(message, code, headers.toMultimap())
        isSuccessful -> Success(body as T, code, headers.toMultimap())
        isClientError -> ClientError(body?.string(), code, headers.toMultimap())
        isServerError -> ServerError(null, body?.string(), code, headers.toMultimap())
        else -> throw IllegalStateException("Unknown error code $code")
    }
}
