package com.beetlestance.aphid.dicebar_kotlin.sprites.jdenticon

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfig
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
    val hues: List<Float>? = null


) : DiceBarConfig()