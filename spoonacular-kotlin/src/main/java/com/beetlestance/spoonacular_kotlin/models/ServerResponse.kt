package com.beetlestance.spoonacular_kotlin.models

internal enum class ResponseType {
    Success, Informational, Redirection, ClientError, ServerError
}

internal abstract class ServerResponse<T>(val responseType: ResponseType) {
    abstract val statusCode: Int
    abstract val headers: Map<String, List<String>>
}

internal class Success<T>(
    val data: T,
    override val statusCode: Int = -1,
    override val headers: Map<String, List<String>> = mapOf()
) : ServerResponse<T>(ResponseType.Success)

internal class Informational<T>(
    val statusText: String,
    override val statusCode: Int = -1,
    override val headers: Map<String, List<String>> = mapOf()
) : ServerResponse<T>(ResponseType.Informational)

internal class Redirection<T>(
    override val statusCode: Int = -1,
    override val headers: Map<String, List<String>> = mapOf()
) : ServerResponse<T>(ResponseType.Redirection)

internal class ClientError<T>(
    val body: Any? = null,
    override val statusCode: Int = -1,
    override val headers: Map<String, List<String>> = mapOf()
) : ServerResponse<T>(ResponseType.ClientError)

internal class ServerError<T>(
    val message: String? = null,
    val body: Any? = null,
    override val statusCode: Int = -1,
    override val headers: Map<String, List<String>>
) : ServerResponse<T>(ResponseType.ServerError)
