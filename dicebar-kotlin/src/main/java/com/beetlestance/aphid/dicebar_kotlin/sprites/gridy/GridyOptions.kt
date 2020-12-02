package com.beetlestance.aphid.dicebar_kotlin.sprites.gridy

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSpriteOptions

object GridyOptions : DiceBarSpriteOptions() {

    const val COLORFUL: String = "colorful"

    const val DETERMINISTIC: String = "deterministic"

    override val spriteConfigOptions: List<String> = listOf(COLORFUL, DETERMINISTIC)
}