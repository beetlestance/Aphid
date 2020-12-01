package com.beetlestance.aphid.dicebar_kotlin.sprites

/**
 *  The following options are available for each avatar style.
 *  @see<a href="https://avatars.dicebear.com/docs/options">Config Options.</a>
 * */
abstract class DiceBarConfig {

    // Avatar border radius
    val radius: Int = 0

    // Return avatar as base64 data uri instead of XML. Not supported by the HTTP API
    val base64: Boolean = false

    // Fixed width
    val width: Int? = null

    // Fixed height
    val height: Int? = null

    // Avatar margin in percent. HTTP-API limitation Max value 25
    val margin: Int = 0

    // Any valid color identifier. HTTP-API limitation Only hex (3-digit, 6-digit and 8-digit)
    // values are allowed. Use url encoded hash: %23.
    val background: String? = null

}


abstract class DiceBarConfigOptions {

    abstract val possibleValues: List<String>
}