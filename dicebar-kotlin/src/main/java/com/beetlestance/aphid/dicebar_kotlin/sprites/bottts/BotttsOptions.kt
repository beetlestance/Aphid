package com.beetlestance.aphid.dicebar_kotlin.sprites.bottts

import androidx.annotation.IntDef
import androidx.annotation.StringDef
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfigOptions
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSpriteOptions

object BotttsColor : DiceBarConfigOptions() {

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

    @StringDef(
        AMBER, BLUE, BLUE_GREY, BROWN, CYAN, DEEP_ORANGE, DEEP_PURPLE, GREEN, GREY, INDIGO,
        LIGHT_BLUE, LIGHT_GREEN, LIME, ORANGE, PINK, PURPLE, RED, TEAL, YELLOW
    )
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(
        AMBER, BLUE, BLUE_GREY, BROWN, CYAN, DEEP_ORANGE, DEEP_PURPLE, GREEN, GREY, INDIGO,
        LIGHT_BLUE, LIGHT_GREEN, LIME, ORANGE, PINK, PURPLE, RED, TEAL, YELLOW
    )
}

object BotttsPrimaryColorLevel : DiceBarConfigOptions() {

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

    @IntDef(
        FIFTY, HUNDRED, TWO_HUNDRED, THREE_HUNDRED, FOUR_HUNDRED, FIVE_HUNDRED, SIX_HUNDRED,
        SEVEN_HUNDRED, EIGHT_HUNDRED, NINE_HUNDRED
    )
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(
        FIFTY.toString(), HUNDRED.toString(), TWO_HUNDRED.toString(), THREE_HUNDRED.toString(),
        FOUR_HUNDRED.toString(), FIVE_HUNDRED.toString(), SIX_HUNDRED.toString(),
        SEVEN_HUNDRED.toString(), EIGHT_HUNDRED.toString(), NINE_HUNDRED.toString()
    )
}

object BotttsSecondaryColorLevel : DiceBarConfigOptions() {

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

    @IntDef(
        FIFTY, HUNDRED, TWO_HUNDRED, THREE_HUNDRED, FOUR_HUNDRED, FIVE_HUNDRED, SIX_HUNDRED,
        SEVEN_HUNDRED, EIGHT_HUNDRED, NINE_HUNDRED
    )
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(
        FIFTY.toString(), HUNDRED.toString(), TWO_HUNDRED.toString(), THREE_HUNDRED.toString(),
        FOUR_HUNDRED.toString(), FIVE_HUNDRED.toString(), SIX_HUNDRED.toString(),
        SEVEN_HUNDRED.toString(), EIGHT_HUNDRED.toString(), NINE_HUNDRED.toString()
    )
}

object BotttsOptions : DiceBarSpriteOptions() {

    private const val COLORS: String = "colors"

    private const val COLORFUL: String = "colorful"

    private const val MOUTH_CHANCE: String = "mouthChance"

    private const val PRIMARY_COLOR_LEVEL: String = "primaryColorLevel"

    private const val SECONDARY_COLOR_LEVEL: String = "secondaryColorLevel"

    private const val SIDES_CHANCE: String = "sidesChance"

    private const val TEXTURE_CHANCE: String = "textureChance"

    private const val TOP_CHANCE: String = "topChance"

    override val spriteConfigOptions: List<String> = listOf(
        COLORS, COLORFUL, MOUTH_CHANCE, PRIMARY_COLOR_LEVEL, SECONDARY_COLOR_LEVEL, SIDES_CHANCE,
        TEXTURE_CHANCE, TOP_CHANCE
    )
}