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

package com.beetlestance.spoonacular_kotlin.models

enum class ResponseType {
    Success, Informational, Redirection, ClientError, ServerError
}

abstract class SpoonacularApiResponse<T>(val responseType: ResponseType) {
    abstract val statusCode: Int
    abstract val headers: Map<String, List<String>>
}

class Success<T>(
    val data: T,
    override val statusCode: Int = -1,
    override val headers: Map<String, List<String>> = mapOf()
) : SpoonacularApiResponse<T>(ResponseType.Success)

class Informational<T>(
    val statusText: String,
    override val statusCode: Int = -1,
    override val headers: Map<String, List<String>> = mapOf()
) : SpoonacularApiResponse<T>(ResponseType.Informational)

class Redirection<T>(
    override val statusCode: Int = -1,
    override val headers: Map<String, List<String>> = mapOf()
) : SpoonacularApiResponse<T>(ResponseType.Redirection)

class ClientError<T>(
    val body: Any? = null,
    override val statusCode: Int = -1,
    override val headers: Map<String, List<String>> = mapOf()
) : SpoonacularApiResponse<T>(ResponseType.ClientError)

class ServerError<T>(
    val message: String? = null,
    val body: Any? = null,
    override val statusCode: Int = -1,
    override val headers: Map<String, List<String>>
) : SpoonacularApiResponse<T>(ResponseType.ServerError)
