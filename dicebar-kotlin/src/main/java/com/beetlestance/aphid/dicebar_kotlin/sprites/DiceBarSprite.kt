package com.beetlestance.aphid.dicebar_kotlin.sprites

import com.beetlestance.aphid.dicebar_kotlin.DiceBarAvatars


abstract class DiceBarSprite {

    @DiceBarAvatars.PossibleValues
    abstract val type: String

    abstract val options: List<String>

}

abstract class DiceBarSpriteOptions {

    protected abstract val spriteConfigOptions: List<String>

    val possibleConfigOptions: List<String> by lazy { globalConfigOptions + spriteConfigOptions }

    companion object {

        const val RADIUS: String = "radius"

        const val BASE64: String = "base64"

        const val WIDTH: String = "width"

        const val HEIGHT: String = "height"

        const val MARGIN: String = "margin"

        const val BACKGROUND: String = "background"

        val globalConfigOptions: List<String> = listOf(
            RADIUS, WIDTH, HEIGHT, MARGIN, BACKGROUND
        )
    }
}

