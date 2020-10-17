package com.beetlestance.data

import com.beetlestance.base.result.Failure
import com.beetlestance.base.result.Result
import com.beetlestance.base.result.Success
import com.beetlestance.spoonacular_kotlin.models.SpoonacularApiResponse
import com.beetlestance.spoonacular_kotlin.models.Success as SpoonacularSuccess

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
