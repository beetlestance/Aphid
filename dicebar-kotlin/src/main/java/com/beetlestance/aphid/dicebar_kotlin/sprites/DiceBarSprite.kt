package com.beetlestance.aphid.dicebar_kotlin.sprites

import com.beetlestance.aphid.dicebar_kotlin.DiceBarAvatar


abstract class DiceBarSprite {

    @DiceBarAvatar.PossibleValues
    abstract val type: String

    abstract val options: List<String>

}

abstract class DiceBarSpriteOptions {

    protected abstract val spriteConfigOptions: List<String>

    val possibleConfigOptions: List<String> by lazy { globalConfigOptions + spriteConfigOptions }

    companion object {

        private const val RADIUS: String = "radius"

        private const val WIDTH: String = "width"

        private const val HEIGHT: String = "height"

        private const val MARGIN: String = "margin"

        private const val BACKGROUND: String = "background"

        private val globalConfigOptions: List<String> = listOf(
            RADIUS, WIDTH, HEIGHT, MARGIN, BACKGROUND
        )
    }
}

