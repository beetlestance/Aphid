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
