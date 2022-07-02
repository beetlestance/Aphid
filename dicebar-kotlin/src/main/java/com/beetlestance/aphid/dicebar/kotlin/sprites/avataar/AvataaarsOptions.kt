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
package com.beetlestance.aphid.dicebar.kotlin.sprites.avataar

import androidx.annotation.StringDef
import com.beetlestance.aphid.dicebar.kotlin.sprites.DiceBarSpriteOptions

@StringDef(AvataaarStyle.CIRCLE, AvataaarStyle.TRANSPARENT)
@Retention(AnnotationRetention.SOURCE)
annotation class AvataaarStyle {
    companion object {
        const val CIRCLE: String = "circle"

        const val TRANSPARENT: String = "transparent"

        val possibleValues: List<String> = listOf(CIRCLE, TRANSPARENT)
    }
}

@StringDef(AvataaarMode.INCLUDE, AvataaarMode.EXCLUDE)
@Retention(AnnotationRetention.SOURCE)
annotation class AvataaarMode {
    companion object {

        const val INCLUDE: String = "include"

        const val EXCLUDE: String = "exclude"

        val possibleValues: List<String> = listOf(INCLUDE, EXCLUDE)
    }
}

@StringDef(
    AvataaarTop.EYE_PATCH,
    AvataaarTop.LONG_HAIR,
    AvataaarTop.HAT,
    AvataaarTop.HIJAB,
    AvataaarTop.SHORT_HAIR,
    AvataaarTop.TURBAN
)
@Retention(AnnotationRetention.SOURCE)
annotation class AvataaarTop {
    companion object {

        const val EYE_PATCH: String = "eyepatch"

        const val LONG_HAIR: String = "longHair"

        const val HAT: String = "hat"

        const val HIJAB: String = "hijab"

        const val SHORT_HAIR: String = "shortHair"

        const val TURBAN: String = "turban"

        val possibleValues: List<String> = listOf(
            EYE_PATCH,
            LONG_HAIR,
            HAT,
            HIJAB,
            SHORT_HAIR,
            TURBAN
        )
    }
}

@StringDef(
    AvataaarHat.BLACK,
    AvataaarHat.BLUE,
    AvataaarHat.GRAY,
    AvataaarHat.HEATHER,
    AvataaarHat.PASTEL,
    AvataaarHat.PINK,
    AvataaarHat.RED,
    AvataaarHat.WHITE
)
@Retention(AnnotationRetention.SOURCE)
annotation class AvataaarHat {
    companion object {

        const val BLACK: String = "black"

        const val BLUE: String = "blue"

        const val GRAY: String = "gray"

        const val HEATHER: String = "heather"

        const val PASTEL: String = "pastel"

        const val PINK: String = "pink"

        const val RED: String = "red"

        const val WHITE: String = "white"

        val possibleValues: List<String> = listOf(
            BLACK,
            BLUE,
            GRAY,
            HEATHER,
            PASTEL,
            PINK,
            RED,
            WHITE
        )
    }
}

@StringDef(
    AvataaarHairColor.AUBURN, AvataaarHairColor.BLACK, AvataaarHairColor.BLONDE,
    AvataaarHairColor.BROWN, AvataaarHairColor.BLUE, AvataaarHairColor.GRAY,
    AvataaarHairColor.PASTEL, AvataaarHairColor.PLATINUM, AvataaarHairColor.RED
)
@Retention(AnnotationRetention.SOURCE)
annotation class AvataaarHairColor {
    companion object {

        const val AUBURN: String = "auburn"

        const val BLACK: String = "black"

        const val BLONDE: String = "blonde"

        const val BROWN: String = "brown"

        const val BLUE: String = "blue"

        const val GRAY: String = "gray"

        const val PASTEL: String = "pastel"

        const val PLATINUM: String = "platinum"

        const val RED: String = "red"

        val possibleValues: List<String> = listOf(
            AUBURN, BLACK, BLONDE, BROWN, BLUE, GRAY, PASTEL, PLATINUM, RED
        )
    }
}

@StringDef(
    AvataaarAccessories.KURT,
    AvataaarAccessories.PRESCRIPTION_01,
    AvataaarAccessories.PRESCRIPTION_02,
    AvataaarAccessories.ROUND,
    AvataaarAccessories.SUNGLASSES,
    AvataaarAccessories.WAYFARERS
)
@Retention(AnnotationRetention.SOURCE)
annotation class AvataaarAccessories {
    companion object {

        const val KURT: String = "kurt"

        const val PRESCRIPTION_01: String = "prescription01"

        const val PRESCRIPTION_02: String = "prescription02"

        const val ROUND: String = "round"

        const val SUNGLASSES: String = "sunglasses"

        const val WAYFARERS: String = "wayfarers"

        val possibleValues: List<String> = listOf(
            KURT,
            PRESCRIPTION_01,
            PRESCRIPTION_02,
            ROUND,
            SUNGLASSES,
            WAYFARERS
        )
    }
}

@StringDef(
    AvataaarAccessoriesColor.BLACK,
    AvataaarAccessoriesColor.BLUE,
    AvataaarAccessoriesColor.GRAY,
    AvataaarAccessoriesColor.HEATHER,
    AvataaarAccessoriesColor.PASTEL,
    AvataaarAccessoriesColor.PINK,
    AvataaarAccessoriesColor.RED,
    AvataaarAccessoriesColor.WHITE
)
@Retention(AnnotationRetention.SOURCE)
annotation class AvataaarAccessoriesColor {
    companion object {

        const val BLACK: String = "black"

        const val BLUE: String = "blue"

        const val GRAY: String = "gray"

        const val HEATHER: String = "heather"

        const val PASTEL: String = "pastel"

        const val PINK: String = "pink"

        const val RED: String = "red"

        const val WHITE: String = "white"

        val possibleValues: List<String> = listOf(
            BLACK,
            BLUE,
            GRAY,
            HEATHER,
            PASTEL,
            PINK,
            RED,
            WHITE
        )
    }
}

@StringDef(
    AvataaarFacialHair.LIGHT,
    AvataaarFacialHair.FANCY,
    AvataaarFacialHair.MAJESTIC,
    AvataaarFacialHair.MAGNUM,
    AvataaarFacialHair.MEDIUM
)
@Retention(AnnotationRetention.SOURCE)
annotation class AvataaarFacialHair {
    companion object {

        const val FANCY: String = "fancy"

        const val LIGHT: String = "light"

        const val MAJESTIC: String = "majestic"

        const val MAGNUM: String = "magnum"

        const val MEDIUM: String = "medium"

        val possibleValues: List<String> = listOf(LIGHT, FANCY, MAJESTIC, MAGNUM, MEDIUM)
    }
}

@StringDef(
    AvataaarFacialHairColor.AUBURN, AvataaarFacialHairColor.BLACK, AvataaarFacialHairColor.BLONDE,
    AvataaarFacialHairColor.BROWN, AvataaarFacialHairColor.BLUE, AvataaarFacialHairColor.GRAY,
    AvataaarFacialHairColor.PASTEL, AvataaarFacialHairColor.PLATINUM, AvataaarFacialHairColor.RED
)
@Retention(AnnotationRetention.SOURCE)
annotation class AvataaarFacialHairColor {
    companion object {

        const val AUBURN: String = "auburn"

        const val BLACK: String = "black"

        const val BLONDE: String = "blonde"

        const val BROWN: String = "brown"

        const val BLUE: String = "blue"

        const val GRAY: String = "gray"

        const val PASTEL: String = "pastel"

        const val PLATINUM: String = "platinum"

        const val RED: String = "red"

        val possibleValues: List<String> = listOf(
            AUBURN, BLACK, BLONDE, BROWN, BLUE, GRAY, PASTEL, PLATINUM, RED
        )
    }
}

@StringDef(
    AvataaarClothes.BLAZER,
    AvataaarClothes.HOODIE,
    AvataaarClothes.OVERALL,
    AvataaarClothes.SWEATER,
    AvataaarClothes.SHIRT
)
@Retention(AnnotationRetention.SOURCE)
annotation class AvataaarClothes {
    companion object {

        const val BLAZER: String = "blazer"

        const val HOODIE: String = "hoodie"

        const val OVERALL: String = "overall"

        const val SWEATER: String = "sweater"

        const val SHIRT: String = "shirt"

        val possibleValues: List<String> = listOf(BLAZER, HOODIE, OVERALL, SWEATER, SHIRT)
    }
}

@StringDef(
    AvataaarClothesColor.BLACK,
    AvataaarClothesColor.BLUE,
    AvataaarClothesColor.GRAY,
    AvataaarClothesColor.HEATHER,
    AvataaarClothesColor.PASTEL,
    AvataaarClothesColor.PINK,
    AvataaarClothesColor.RED,
    AvataaarClothesColor.WHITE
)
@Retention(AnnotationRetention.SOURCE)
annotation class AvataaarClothesColor {
    companion object {

        const val BLACK: String = "black"

        const val BLUE: String = "blue"

        const val GRAY: String = "gray"

        const val HEATHER: String = "heather"

        const val PASTEL: String = "pastel"

        const val PINK: String = "pink"

        const val RED: String = "red"

        const val WHITE: String = "white"

        val possibleValues: List<String> = listOf(
            BLACK,
            BLUE,
            GRAY,
            HEATHER,
            PASTEL,
            PINK,
            RED,
            WHITE
        )
    }
}

@StringDef(
    AvataaarEyes.CLOSE, AvataaarEyes.CRY, AvataaarEyes.DEFAULT, AvataaarEyes.DIZZY,
    AvataaarEyes.HAPPY, AvataaarEyes.HEARTS, AvataaarEyes.SIDE, AvataaarEyes.SQUIT,
    AvataaarEyes.SURPRISED, AvataaarEyes.ROLL, AvataaarEyes.WINK, AvataaarEyes.WINK_WACKY
)
@Retention(AnnotationRetention.SOURCE)
annotation class AvataaarEyes {
    companion object {

        const val CLOSE: String = "close"

        const val CRY: String = "cry"

        const val DEFAULT: String = "default"

        const val DIZZY: String = "dizzy"

        const val HAPPY: String = "happy"

        const val HEARTS: String = "hearts"

        const val ROLL: String = "roll"

        const val SIDE: String = "side"

        const val SQUIT: String = "squit"

        const val SURPRISED: String = "surprised"

        const val WINK: String = "wink"

        const val WINK_WACKY: String = "winkWacky"

        val possibleValues: List<String> = listOf(
            CLOSE, CRY, DEFAULT, DIZZY, HAPPY, HEARTS, SIDE,
            SQUIT, SURPRISED, ROLL, WINK, WINK_WACKY
        )
    }
}

@StringDef(
    AvataaarEyeBrow.ANGRY,
    AvataaarEyeBrow.DEFAULT,
    AvataaarEyeBrow.FLAT,
    AvataaarEyeBrow.FROWN,
    AvataaarEyeBrow.RAISED,
    AvataaarEyeBrow.SAD,
    AvataaarEyeBrow.UNIBROW,
    AvataaarEyeBrow.UP
)
@Retention(AnnotationRetention.SOURCE)
annotation class AvataaarEyeBrow {
    companion object {

        const val ANGRY: String = "angry"

        const val DEFAULT: String = "default"

        const val FLAT: String = "flat"

        const val FROWN: String = "frown"

        const val RAISED: String = "raised"

        const val SAD: String = "sad"

        const val UNIBROW: String = "unibrow"

        const val UP: String = "up"

        val possibleValues: List<String> = listOf(
            ANGRY,
            DEFAULT,
            FLAT,
            FROWN,
            RAISED,
            SAD,
            UNIBROW,
            UP
        )
    }
}

@StringDef(
    AvataaarMouth.CONCERNED, AvataaarMouth.DEFAULT, AvataaarMouth.DISBELIEF, AvataaarMouth.EATING,
    AvataaarMouth.GRIMACE, AvataaarMouth.SAD, AvataaarMouth.SCREAM, AvataaarMouth.SERIOUS,
    AvataaarMouth.SMILE, AvataaarMouth.TONGUE, AvataaarMouth.TWINKLE, AvataaarMouth.VOMIT
)
@Retention(AnnotationRetention.SOURCE)
annotation class AvataaarMouth {
    companion object {

        const val CONCERNED: String = "concerned"

        const val DEFAULT: String = "default"

        const val DISBELIEF: String = "disbelief"

        const val EATING: String = "eating"

        const val GRIMACE: String = "grimace"

        const val SAD: String = "sad"

        const val SCREAM: String = "scream"

        const val SERIOUS: String = "serious"

        const val SMILE: String = "smile"

        const val TONGUE: String = "tongue"

        const val TWINKLE: String = "twinkle"

        const val VOMIT: String = "vomit"

        val possibleValues: List<String> = listOf(
            CONCERNED, DEFAULT, DISBELIEF, EATING, GRIMACE, SAD,
            SCREAM, SERIOUS, SMILE, TONGUE, TWINKLE, VOMIT
        )
    }
}

@StringDef(
    AvataaarSkin.BLACK,
    AvataaarSkin.BROWN,
    AvataaarSkin.DARK_BROWN,
    AvataaarSkin.LIGHT,
    AvataaarSkin.PALE,
    AvataaarSkin.TANNED,
    AvataaarSkin.YELLOW
)
@Retention(AnnotationRetention.SOURCE)
annotation class AvataaarSkin {
    companion object {

        const val BLACK: String = "black"

        const val BROWN: String = "brown"

        const val DARK_BROWN: String = "darkBrown"

        const val LIGHT: String = "light"

        const val PALE: String = "pale"

        const val TANNED: String = "tanned"

        const val YELLOW: String = "yellow"

        val possibleValues: List<String> = listOf(
            BLACK,
            BROWN,
            DARK_BROWN,
            LIGHT,
            PALE,
            TANNED,
            YELLOW
        )
    }
}

object AvataaarOptions : DiceBarSpriteOptions() {

    const val ACCESSORIES: String = "accessories"

    const val ACCESSORIES_CHANCE: String = "accessoriesChance"

    const val ACCESSORIES_COLOR: String = "accessoriesColor"

    const val CLOTHES: String = "clothes"

    const val CLOTHES_COLOR: String = "clothesColor"

    const val EYES: String = "eyes"

    const val EYEBROW: String = "eyebrow"

    const val FACIAL_HAIR: String = "facialHair"

    const val FACIAL_HAIR_CHANCE: String = "facialHairChance"

    const val FACIAL_HAIR_COLOR: String = "facialHairColor"

    const val HAT_COLOR: String = "hatColor"

    const val HAIR_COLOR: String = "hairColor"

    const val MODE: String = "mode"

    const val MOUTH: String = "mouth"

    const val SKIN: String = "skin"

    const val STYLE: String = "style"

    const val TOP: String = "top"

    const val TOP_CHANCE: String = "topChance"

    override val spriteConfigOptions: List<String> = listOf(
        ACCESSORIES, ACCESSORIES_CHANCE, ACCESSORIES_COLOR, CLOTHES, CLOTHES_COLOR, EYES,
        EYEBROW, FACIAL_HAIR, FACIAL_HAIR_CHANCE, FACIAL_HAIR_COLOR, HAT_COLOR, HAIR_COLOR,
        MODE, MOUTH, SKIN, STYLE, TOP, TOP_CHANCE
    )
}
