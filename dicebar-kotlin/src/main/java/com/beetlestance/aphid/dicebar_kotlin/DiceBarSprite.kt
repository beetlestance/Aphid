package com.beetlestance.aphid.dicebar_kotlin


abstract class DiceBarSprite {

    @DiceBarAvatar.PossibleValues
    abstract val type: String

    abstract val options: List<String>

}

abstract class DiceBarSpriteOptions {

    protected abstract val spriteSeedValues: List<String>

    val possibleSeedValues: List<String> by lazy { globalSeedValues + spriteSeedValues }

    companion object {

        private const val RADIUS: String = "radius"

        private const val WIDTH: String = "width"

        private const val HEIGHT: String = "height"

        private const val MARGIN: String = "margin"

        private const val BACKGROUND: String = "background"

        private val globalSeedValues: List<String> = listOf(
            RADIUS, WIDTH, HEIGHT, MARGIN, BACKGROUND
        )
    }
}

