/*
 * Copyright 2021 BeetleStance
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beetlestance.aphid.dicebar_kotlin.sprites.bottts

import androidx.annotation.IntDef
import androidx.annotation.StringDef
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSpriteOptions

@StringDef(
    BotttsColor.AMBER, BotttsColor.BLUE, BotttsColor.BLUE_GREY, BotttsColor.BROWN,
    BotttsColor.CYAN, BotttsColor.DEEP_ORANGE, BotttsColor.DEEP_PURPLE, BotttsColor.GREEN,
    BotttsColor.GREY, BotttsColor.INDIGO, BotttsColor.LIGHT_BLUE, BotttsColor.LIGHT_GREEN,
    BotttsColor.LIME, BotttsColor.ORANGE, BotttsColor.PINK, BotttsColor.PURPLE,
    BotttsColor.RED, BotttsColor.TEAL, BotttsColor.YELLOW
)
@Retention(AnnotationRetention.SOURCE)
annotation class BotttsColor {

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
    BotttsPrimaryColorLevel.FIFTY,
    BotttsPrimaryColorLevel.HUNDRED,
    BotttsPrimaryColorLevel.TWO_HUNDRED,
    BotttsPrimaryColorLevel.THREE_HUNDRED,
    BotttsPrimaryColorLevel.FOUR_HUNDRED,
    BotttsPrimaryColorLevel.FIVE_HUNDRED,
    BotttsPrimaryColorLevel.SIX_HUNDRED,
    BotttsPrimaryColorLevel.SEVEN_HUNDRED,
    BotttsPrimaryColorLevel.EIGHT_HUNDRED,
    BotttsPrimaryColorLevel.NINE_HUNDRED
)
@Retention(AnnotationRetention.SOURCE)
annotation class BotttsPrimaryColorLevel {
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

@IntDef(
    BotttsSecondaryColorLevel.FIFTY,
    BotttsSecondaryColorLevel.HUNDRED,
    BotttsSecondaryColorLevel.TWO_HUNDRED,
    BotttsSecondaryColorLevel.THREE_HUNDRED,
    BotttsSecondaryColorLevel.FOUR_HUNDRED,
    BotttsSecondaryColorLevel.FIVE_HUNDRED,
    BotttsSecondaryColorLevel.SIX_HUNDRED,
    BotttsSecondaryColorLevel.SEVEN_HUNDRED,
    BotttsSecondaryColorLevel.EIGHT_HUNDRED,
    BotttsSecondaryColorLevel.NINE_HUNDRED
)
@Retention(AnnotationRetention.SOURCE)
annotation class BotttsSecondaryColorLevel {

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

object BotttsOptions : DiceBarSpriteOptions() {

    const val COLORS: String = "colors"

    const val COLORFUL: String = "colorful"

    const val MOUTH_CHANCE: String = "mouthChance"

    const val PRIMARY_COLOR_LEVEL: String = "primaryColorLevel"

    const val SECONDARY_COLOR_LEVEL: String = "secondaryColorLevel"

    const val SIDES_CHANCE: String = "sidesChance"

    const val TEXTURE_CHANCE: String = "textureChance"

    const val TOP_CHANCE: String = "topChance"

    override val spriteConfigOptions: List<String> = listOf(
        COLORS, COLORFUL, MOUTH_CHANCE, PRIMARY_COLOR_LEVEL, SECONDARY_COLOR_LEVEL, SIDES_CHANCE,
        TEXTURE_CHANCE, TOP_CHANCE
    )
}
