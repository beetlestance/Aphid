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
package com.beetlestance.aphid.dicebar_kotlin.sprites.identicon

import androidx.annotation.IntDef
import androidx.annotation.StringDef
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSpriteOptions

@StringDef(
    IdenticonColor.AMBER,
    IdenticonColor.BLUE,
    IdenticonColor.BLUE_GREY,
    IdenticonColor.BROWN,
    IdenticonColor.CYAN,
    IdenticonColor.DEEP_ORANGE,
    IdenticonColor.DEEP_PURPLE,
    IdenticonColor.GREEN,
    IdenticonColor.GREY,
    IdenticonColor.INDIGO,
    IdenticonColor.LIGHT_BLUE,
    IdenticonColor.LIGHT_GREEN,
    IdenticonColor.LIME,
    IdenticonColor.ORANGE,
    IdenticonColor.PINK,
    IdenticonColor.PURPLE,
    IdenticonColor.RED,
    IdenticonColor.TEAL,
    IdenticonColor.YELLOW
)
@Retention(AnnotationRetention.SOURCE)
annotation class IdenticonColor {
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
    IdenticonColorLevel.FIFTY,
    IdenticonColorLevel.HUNDRED,
    IdenticonColorLevel.TWO_HUNDRED,
    IdenticonColorLevel.THREE_HUNDRED,
    IdenticonColorLevel.FOUR_HUNDRED,
    IdenticonColorLevel.FIVE_HUNDRED,
    IdenticonColorLevel.SIX_HUNDRED,
    IdenticonColorLevel.SEVEN_HUNDRED,
    IdenticonColorLevel.EIGHT_HUNDRED,
    IdenticonColorLevel.NINE_HUNDRED
)
@Retention(AnnotationRetention.SOURCE)
annotation class IdenticonColorLevel {

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

object IdenticonOptions : DiceBarSpriteOptions() {

    const val COLORS: String = "colors"

    const val COLOR_LEVEL: String = "colorLevel"

    override val spriteConfigOptions: List<String> = listOf(COLORS, COLOR_LEVEL)
}
