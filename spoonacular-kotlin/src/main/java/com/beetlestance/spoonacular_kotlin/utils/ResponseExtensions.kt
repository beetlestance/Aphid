package com.beetlestance.spoonacular_kotlin.utils

import com.beetlestance.spoonacular_kotlin.models.ClientError
import com.beetlestance.spoonacular_kotlin.models.Informational
import com.beetlestance.spoonacular_kotlin.models.Redirection
import com.beetlestance.spoonacular_kotlin.models.ServerError
import com.beetlestance.spoonacular_kotlin.models.SpoonacularApiResponse
import com.beetlestance.spoonacular_kotlin.models.Success
import com.beetlestance.spoonacular_kotlin.utils.MoshiSerializer.Companion.moshi
import okhttp3.Response
import java.util.*

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


inline fun <reified T> Response.toSpoonacularApiResponse(): SpoonacularApiResponse<T?>? {

    return when {
        isRedirect -> Redirection(code, headers.toMultimap())
        isInformational -> Informational(message, code, headers.toMultimap())
        isSuccessful -> Success(spoonacularResponseBody(), code, headers.toMultimap())
        isClientError -> ClientError(body?.string(), code, headers.toMultimap())
        isServerError -> ServerError(null, body?.string(), code, headers.toMultimap())
        else -> throw IllegalStateException("Unknown error code $code")
    }
}

inline fun <reified T : Any?> Response.spoonacularResponseBody(): T? {
    val bodyContent = (body ?: return null).string()
    if (bodyContent.isBlank()) return null

    val mediaType = header(MoshiSerializer.ContentType)?.substringBefore(delimiter = ";")
        ?.toLowerCase(Locale.ENGLISH)

    return when (mediaType) {
        MoshiSerializer.JsonMediaType -> moshi.adapter(T::class.java).fromJson(bodyContent)
        else -> throw Exception("ResponseBody currently only supports JSON body.")
    }
}
