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
package com.beetlestance.base.result

/**
 * A generic class that holds a successful outcome with a value of type [T] or a
 * failure with an arbitrary [Throwable] exception
 */
sealed class Result<T>{

    // Returns `true` if this instance represents a successful outcome.
    val isSuccess: Boolean get() = this is Success

    // Returns `true` if this instance represents a failed outcome.
    val isError: Boolean get() = this is Error
}

/**
 * Represents an instance that holds the given [data] as successful value.
 */
data class Success<T>(val data: T) : Result<T>()

/**
 * Represents an instance that holds the given [throwable] as error value.
 */
data class Error<T>(val throwable: Throwable): Result<T>()

/**
 * Returns the holding value if the instance is [Success] or
 * throws the holding throwable if the instance is [Error]
 */
fun <T> Result<T>.dataOrThrow() = when(this){
    is Success -> data
    is Error -> throw throwable
}

/**
 * Returns the holding value if the instance is [Success] or
 * returns the provided value if the instance is [Error]
 */
fun <T> Result<T>.dataOrElse(value: T): T = when(this){
    is Success -> data
    is Error -> value
}

/**
 * Performs the given [action] on the holding data fi the instance is [Success].
 * Returns the original result unchanged.
 */
inline fun <T> Result<T>.onSuccess(action: (value: T) -> Unit): Result<T>{
    if(this is Success) action(data)
    return this
}

/**
 * Performs the given [action] on the holding throwable if the instance is [Error].
 * Returns the original result unchanged.
 */
inline fun <T> Result<T>.onError(action: (exception: Throwable) -> Unit): Result<T> {
    if(this is Error) action(throwable)
    return this
}