package com.beetlestance.aphid.dicebar_kotlin.sprites.jdenticon

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfig
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSpriteOptions
import com.squareup.moshi.Json

/**
 * The value of [DiceBarConfig] can be anything you like - but don't use any sensitive or personal data here!
 *
 * @see <a href="https://avatars.dicebear.com/styles/jdenticon">Jdenticon Config Options</a>
 * */

data class JdenticonConfig(

    // Colored shapes - Lightness
    @Json(name = JdenticonOptions.COLOR_LIGHTNESS)
    val colorLightness: List<Float>? = null,

    // Colored shapes - Saturation
    @Json(name = JdenticonOptions.COLOR_SATURATION)
    val colorSaturation: List<Float>? = null,

    // Grayscale shapes - Lightness
    @Json(name = JdenticonOptions.GRAYSCALE_LIGHTNESS)
    val grayscaleLightness: List<Float>? = null,

    // Grayscale shapes - Saturation
    @Json(name = JdenticonOptions.GRAYSCALE_SATURATION)
    val grayscaleSaturation: List<Float>? = null,

    // Icon hue
    @Json(name = JdenticonOptions.HUES)
    val hues: List<Float>? = null,

    @Json(name = DiceBarSpriteOptions.RADIUS)
    override val radius: Int = 0,

    // Fixed width
    @Json(name = DiceBarSpriteOptions.WIDTH)
    override val width: Int? = null,

    // Fixed height
    @Json(name = DiceBarSpriteOptions.HEIGHT)
    override val height: Int? = null,

    // Avatar margin in percent. HTTP-API limitation Max value 25
    @Json(name = DiceBarSpriteOptions.MARGIN)
    override val margin: Int = 0,

    // Any valid color identifier. HTTP-API limitation Only hex (3-digit, 6-digit and 8-digit)
    // values are allowed. Use url encoded hash: %23.
    @Json(name = DiceBarSpriteOptions.BACKGROUND)
    override val background: String? = null

) : DiceBarConfig