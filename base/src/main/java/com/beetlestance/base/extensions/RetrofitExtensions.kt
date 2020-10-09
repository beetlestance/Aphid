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
package com.beetlestance.base.extensions

import com.beetlestance.base.result.Failure
import com.beetlestance.base.result.Result
import com.beetlestance.base.result.Success
import com.beetlestance.spoonacular_kotlin.models.SpoonacularApiResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import com.beetlestance.spoonacular_kotlin.models.Success as SpoonacularSuccess

fun <T> Response<T>.bodyOrThrowException(): T {
    if (!isSuccessful) throw toException()
    return body()!!
}

fun <T> Response<T>.toException() = HttpException(this)

suspend inline fun <T> Call<T>.executeWithRetry(
    defaultDelay: Long = 100,
    maxAttempts: Int = 3,
    shouldRetry: (Exception) -> Boolean
): Response<T> {
    repeat(maxAttempts) { attempt ->
        val nextDelay = attempt * attempt * defaultDelay

        try {
            val call = if (isExecuted) clone() else this
            return call.executeSynchronous()
        } catch (e: Exception) {
            if (attempt == (maxAttempts - 1) || !shouldRetry(e)) {
                throw e
            }
        }

        delay(nextDelay)
    }

    throw IllegalArgumentException("Unknown exception from executeWithRetry")
}

suspend inline fun <T> Call<T>.fetchBodyWithRetry(
    defaultDelay: Long = 100,
    maxAttempts: Int = 3,
    shouldRetry: (Exception) -> Boolean
): T = executeWithRetry(defaultDelay, maxAttempts, shouldRetry).bodyOrThrowException()

fun defaultShouldRetry(exception: Exception) = when (exception) {
    is HttpException -> exception.code() == 429
    is IOException -> true
    else -> false
}

suspend fun <T> Call<T>.executeSynchronous(): Response<T> {
    return suspendCancellableCoroutine { cont ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                cont.resume(response) { throwable -> cont.cancel(throwable) }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                cont.cancel(t)
            }
        })
    }
}

fun <T> Response<T>.toResult(): Result<T> = try {
    if (isSuccessful) {
        Success(data = bodyOrThrowException())
    } else {
        Failure(toException())
    }
} catch (e: Exception) {
    Failure(e)
}

suspend fun <T, E> Response<T>.toResult(mapper: suspend (T) -> E): Result<E> = try {
    if (isSuccessful) {
        Success(data = mapper(bodyOrThrowException()))
    } else {
        Failure(toException())
    }
} catch (e: Exception) {
    Failure(e)
}

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
