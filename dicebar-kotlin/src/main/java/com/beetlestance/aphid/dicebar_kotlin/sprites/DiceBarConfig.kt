package com.beetlestance.aphid.dicebar_kotlin.sprites

import com.squareup.moshi.Json

/**
 *  The following options are available for each avatar style.
 *  @see<a href="https://avatars.dicebear.com/docs/options">Config Options.</a>
 * */
open class DiceBarConfig {

    // Avatar border radius
    @Json(name = DiceBarSpriteOptions.RADIUS)
    open val radius: Int = 0

    // Return avatar as base64 data uri instead of XML. Not supported by the HTTP API
    @Json(name = DiceBarSpriteOptions.BASE64)
    open val base64: Boolean = false

    // Fixed width
    @Json(name = DiceBarSpriteOptions.WIDTH)
    open val width: Int? = null

    // Fixed height
    @Json(name = DiceBarSpriteOptions.HEIGHT)
    open val height: Int? = null

    // Avatar margin in percent. HTTP-API limitation Max value 25
    @Json(name = DiceBarSpriteOptions.MARGIN)
    open val margin: Int = 0

    // Any valid color identifier. HTTP-API limitation Only hex (3-digit, 6-digit and 8-digit)
    // values are allowed. Use url encoded hash: %23.
    @Json(name = DiceBarSpriteOptions.BACKGROUND)
    open val background: String? = null

}


abstract class DiceBarConfigOptions {

    abstract val possibleValues: List<String>
}