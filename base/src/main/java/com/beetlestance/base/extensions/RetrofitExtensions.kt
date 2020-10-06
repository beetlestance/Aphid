package com.beetlestance.base.extensions

import com.beetlestance.base.result.Failure
import com.beetlestance.base.result.Result
import com.beetlestance.base.result.Success
import com.beetlestance.spoonacular_kotlin.models.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import com.beetlestance.spoonacular_kotlin.models.Success as SpoonacularSuccess

fun <T> Response<T>.bodyOrThrow(): T {
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
): T = executeWithRetry(defaultDelay, maxAttempts, shouldRetry).bodyOrThrow()

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
        Success(data = bodyOrThrow())
    } else {
        Failure(toException())
    }
} catch (e: Exception) {
    Failure(e)
}

suspend fun <T, E> Response<T>.toResult(mapper: suspend (T) -> E): Result<E> = try {
    if (isSuccessful) {
        Success(data = mapper(bodyOrThrow()))
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

