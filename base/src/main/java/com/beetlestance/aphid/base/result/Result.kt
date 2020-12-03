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
package com.beetlestance.aphid.base.result

/**
 * A generic class that holds a successful outcome with a value of type [T] or a
 * failure with an arbitrary [Throwable] exception
 */
sealed class Result<T> {

    // Returns `true` if this instance represents a successful outcome.
    val isSuccess: Boolean get() = this is Success

    // Returns `true` if this instance represents a failed outcome.
    val isFailure: Boolean get() = this is Failure
}

/**
 * Represents an instance that holds the given [data] as successful value.
 */
data class Success<T>(val data: T) : Result<T>()

/**
 * Represents an instance that holds the given [throwable] as error value.
 */
data class Failure<T>(val throwable: Throwable) : Result<T>()

/**
 * Returns the holding value if the instance is [Success] or
 * throws the holding throwable if the instance is [Failure]
 *
 * Performs the give [block] if the instance is [Success]
 */
suspend fun <T> Result<T>.dataOrThrowException(block: suspend T.() -> Unit = {}): T = when (this) {
    is Success -> {
        block(data)
        data
    }
    is Failure -> throw throwable
}

/**
 * Returns the holding value if the instance is [Success] or
 * returns the provided value if the instance is [Failure]
 */
fun <T> Result<T>.dataOrElse(value: T): T = when (this) {
    is Success -> data
    is Failure -> value
}

/**
 * Returns the holding value if the instance is [Success] or
 * returns `null` if the instance is [Failure]
 */
fun <T> Result<T>.dataOrNull(): T? = when (this) {
    is Success -> data
    is Failure -> null
}

/**
 * Performs the given [action] on the holding data fi the instance is [Success].
 * Returns the original result unchanged.
 */
inline fun <T> Result<T>.onSuccess(action: (value: T) -> Unit): Result<T> {
    if (this is Success) action(data)
    return this
}

/**
 * Performs the given [action] on the holding throwable if the instance is [Failure].
 * Returns the original result unchanged.
 */
inline fun <T> Result<T>.onFailure(action: (exception: Throwable) -> Unit): Result<T> {
    if (this is Failure) action(throwable)
    return this
}
