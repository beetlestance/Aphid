package com.beetlestance.spoonacular_kotlin.exceptions

internal open class ClientException : RuntimeException {

    /**
     * Constructs an [ClientException] with no detail message.
     */
    constructor(e: Exception) : super(e)

    /**
     * Constructs an [ClientException] with the specified detail message.

     * @param   message   the detail message.
     */
    constructor(message: String) : super(message)

}

