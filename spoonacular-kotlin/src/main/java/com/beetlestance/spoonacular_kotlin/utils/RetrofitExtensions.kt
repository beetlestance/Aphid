package com.beetlestance.spoonacular_kotlin.utils

import com.beetlestance.spoonacular_kotlin.models.SpoonacularApiResponse
import retrofit2.Response

inline fun <reified T> Response<T>.toSpoonacularApiResponse(): SpoonacularApiResponse<T?>? {
    return raw().toSpoonacularApiResponse()
}
