package com.beetlestance.spoonacular_kotlin.exceptions

internal open class ServerException : RuntimeException {

    /**
     * Constructs an [ServerException] with no detail message.
     */
    constructor(e: Exception) : super(e)

    /**
     * Constructs an [ServerException] with the specified detail message.

     * @param   message   the detail message.
     */
    constructor(message: String) : super(message)

}
