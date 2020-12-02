package com.beetlestance.aphid.dicebar_kotlin.sprites

/**
 *  The following options are available for each avatar style.
 *  @see<a href="https://avatars.dicebear.com/docs/options">Config Options.</a>
 * */
interface DiceBarConfig {

    // Avatar border radius
    val radius: Int

    // Fixed width
    val width: Int?

    // Fixed height
    val height: Int?

    // Avatar margin in percent. HTTP-API limitation Max value 25
    val margin: Int

    // Any valid color identifier. HTTP-API limitation Only hex (3-digit, 6-digit and 8-digit)
    // values are allowed. Use url encoded hash: %23.
    val background: String?
}


abstract class DiceBarConfigOptions {

    abstract val possibleValues: List<String>
}