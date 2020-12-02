package com.beetlestance.aphid.dicebar_kotlin.sprites.jdenticon

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSpriteOptions

object JdenticonOptions : DiceBarSpriteOptions() {

    const val COLOR_LIGHTNESS: String = "colorLightness"

    const val COLOR_SATURATION: String = "colorSaturation"

    const val GRAYSCALE_LIGHTNESS: String = "grayscaleLightness"

    const val GRAYSCALE_SATURATION: String = "grayscaleSaturation"

    const val HUES: String = "hues"

    override val spriteConfigOptions: List<String> = listOf(
        COLOR_LIGHTNESS, COLOR_SATURATION, GRAYSCALE_LIGHTNESS, GRAYSCALE_SATURATION, HUES
    )
}