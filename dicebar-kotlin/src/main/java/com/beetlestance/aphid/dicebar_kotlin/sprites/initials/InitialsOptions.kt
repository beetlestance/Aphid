package com.beetlestance.aphid.dicebar_kotlin.sprites.initials

import androidx.annotation.IntDef
import androidx.annotation.StringDef
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSpriteOptions

@StringDef(
    InitialsBackgroundColor.AMBER,
    InitialsBackgroundColor.BLUE,
    InitialsBackgroundColor.BLUE_GREY,
    InitialsBackgroundColor.BROWN,
    InitialsBackgroundColor.CYAN,
    InitialsBackgroundColor.DEEP_ORANGE,
    InitialsBackgroundColor.DEEP_PURPLE,
    InitialsBackgroundColor.GREEN,
    InitialsBackgroundColor.GREY,
    InitialsBackgroundColor.INDIGO,
    InitialsBackgroundColor.LIGHT_BLUE,
    InitialsBackgroundColor.LIGHT_GREEN,
    InitialsBackgroundColor.LIME,
    InitialsBackgroundColor.ORANGE,
    InitialsBackgroundColor.PINK,
    InitialsBackgroundColor.PURPLE,
    InitialsBackgroundColor.RED,
    InitialsBackgroundColor.TEAL,
    InitialsBackgroundColor.YELLOW
)
@Retention(AnnotationRetention.SOURCE)
annotation class InitialsBackgroundColor {

    companion object {
        const val AMBER: String = "amber"

        const val BLUE: String = "blue"

        const val BLUE_GREY: String = "blueGrey"

        const val BROWN: String = "brown"

        const val CYAN: String = "cyan"

        const val DEEP_ORANGE: String = "deepOrange"

        const val DEEP_PURPLE: String = "deepPurple"

        const val GREEN: String = "green"

        const val GREY: String = "grey"

        const val INDIGO: String = "indigo"

        const val LIGHT_BLUE: String = "lightBlue"

        const val LIGHT_GREEN: String = "lightGreen"

        const val LIME: String = "lime"

        const val ORANGE: String = "orange"

        const val PINK: String = "pink"

        const val PURPLE: String = "purple"

        const val RED: String = "red"

        const val TEAL: String = "teal"

        const val YELLOW: String = "yellow"

        val possibleValues: List<String> = listOf(
            AMBER, BLUE, BLUE_GREY, BROWN, CYAN, DEEP_ORANGE, DEEP_PURPLE, GREEN, GREY, INDIGO,
            LIGHT_BLUE, LIGHT_GREEN, LIME, ORANGE, PINK, PURPLE, RED, TEAL, YELLOW
        )
    }
}

@IntDef(
    InitialsBackgroundColorLevel.FIFTY,
    InitialsBackgroundColorLevel.HUNDRED,
    InitialsBackgroundColorLevel.TWO_HUNDRED,
    InitialsBackgroundColorLevel.THREE_HUNDRED,
    InitialsBackgroundColorLevel.FOUR_HUNDRED,
    InitialsBackgroundColorLevel.FIVE_HUNDRED,
    InitialsBackgroundColorLevel.SIX_HUNDRED,
    InitialsBackgroundColorLevel.SEVEN_HUNDRED,
    InitialsBackgroundColorLevel.EIGHT_HUNDRED,
    InitialsBackgroundColorLevel.NINE_HUNDRED
)
@Retention(AnnotationRetention.SOURCE)
annotation class InitialsBackgroundColorLevel {
    companion object {
        const val FIFTY: Int = 50

        const val HUNDRED: Int = 100

        const val TWO_HUNDRED: Int = 200

        const val THREE_HUNDRED: Int = 300

        const val FOUR_HUNDRED: Int = 400

        const val FIVE_HUNDRED: Int = 500

        const val SIX_HUNDRED: Int = 600

        const val SEVEN_HUNDRED: Int = 700

        const val EIGHT_HUNDRED: Int = 800

        const val NINE_HUNDRED: Int = 900

        val possibleValues: List<String> = listOf(
            FIFTY.toString(), HUNDRED.toString(), TWO_HUNDRED.toString(), THREE_HUNDRED.toString(),
            FOUR_HUNDRED.toString(), FIVE_HUNDRED.toString(), SIX_HUNDRED.toString(),
            SEVEN_HUNDRED.toString(), EIGHT_HUNDRED.toString(), NINE_HUNDRED.toString()
        )
    }
}

object InitialsOptions : DiceBarSpriteOptions() {

    const val BACKGROUND_COLORS: String = "backgroundColors"

    const val BACKGROUND_COLOR_LEVEL: String = "backgroundColorLevel"

    const val FONT_SIZE: String = "fontSize"

    const val CHARS: String = "chars"

    const val BOLD: String = "bold"

    override val spriteConfigOptions: List<String> = listOf(
        BACKGROUND_COLORS, BACKGROUND_COLOR_LEVEL, FONT_SIZE, CHARS, BOLD
    )
}