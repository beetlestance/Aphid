package com.beetlestance.aphid.dicebar_kotlin.sprites.avataar

import androidx.annotation.StringDef
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfigOptions
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSpriteOptions

object AvataaarStyle : DiceBarConfigOptions() {

    const val CIRCLE: String = "circle"

    const val TRANSPARENT: String = "transparent"

    @StringDef(CIRCLE, TRANSPARENT)
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(CIRCLE, TRANSPARENT)
}


object AvataaarMode : DiceBarConfigOptions() {

    const val INCLUDE: String = "include"

    const val EXCLUDE: String = "exclude"

    @StringDef(INCLUDE, EXCLUDE)
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(INCLUDE, EXCLUDE)
}

object AvataaarTop : DiceBarConfigOptions() {

    const val EYE_PATCH: String = "eyepatch"

    const val LONG_HAIR: String = "longHair"

    const val HAT: String = "hat"

    const val HIJAB: String = "hijab"

    const val SHORT_HAIR: String = "shortHair"

    const val TURBAN: String = "turban"

    @StringDef(EYE_PATCH, LONG_HAIR, HAT, HIJAB, SHORT_HAIR, TURBAN)
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(
        EYE_PATCH, LONG_HAIR, HAT, HIJAB, SHORT_HAIR, TURBAN
    )
}

object AvataaarHat : DiceBarConfigOptions() {

    const val BLACK: String = "black"

    const val BLUE: String = "blue"

    const val GRAY: String = "gray"

    const val HEATHER: String = "heather"

    const val PASTEL: String = "pastel"

    const val PINK: String = "pink"

    const val RED: String = "red"

    const val WHITE: String = "white"

    @StringDef(BLACK, BLUE, GRAY, HEATHER, PASTEL, PINK, RED, WHITE)
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(
        BLACK, BLUE, GRAY, HEATHER, PASTEL, PINK, RED, WHITE
    )
}

object AvataaarHairColor : DiceBarConfigOptions() {

    const val AUBURN: String = "auburn"

    const val BLACK: String = "black"

    const val BLONDE: String = "blonde"

    const val BROWN: String = "brown"

    const val BLUE: String = "blue"

    const val GRAY: String = "gray"

    const val PASTEL: String = "pastel"

    const val PLATINUM: String = "platinum"

    const val RED: String = "red"

    @StringDef(AUBURN, BLACK, BLONDE, BROWN, BLUE, GRAY, PASTEL, PLATINUM, RED)
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(
        AUBURN, BLACK, BLONDE, BROWN, BLUE, GRAY, PASTEL, PLATINUM, RED
    )
}

object AvataaarAccessories : DiceBarConfigOptions() {

    const val KURT: String = "kurt"

    const val PRESCRIPTION_01: String = "prescription01"

    const val PRESCRIPTION_02: String = "prescription02"

    const val ROUND: String = "round"

    const val SUNGLASSES: String = "sunglasses"

    const val WAYFARERS: String = "wayfarers"

    @StringDef(KURT, PRESCRIPTION_01, PRESCRIPTION_02, ROUND, SUNGLASSES, WAYFARERS)
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(
        KURT, PRESCRIPTION_01, PRESCRIPTION_02, ROUND, SUNGLASSES, WAYFARERS
    )
}

object AvataaarAccessoriesColor : DiceBarConfigOptions() {

    const val BLACK: String = "black"

    const val BLUE: String = "blue"

    const val GRAY: String = "gray"

    const val HEATHER: String = "heather"

    const val PASTEL: String = "pastel"

    const val PINK: String = "pink"

    const val RED: String = "red"

    const val WHITE: String = "white"

    @StringDef(BLACK, BLUE, GRAY, HEATHER, PASTEL, PINK, RED, WHITE)
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(
        BLACK, BLUE, GRAY, HEATHER, PASTEL, PINK, RED, WHITE
    )
}

object AvataaarFacialHair : DiceBarConfigOptions() {

    const val FANCY: String = "fancy"

    const val LIGHT: String = "light"

    const val MAJESTIC: String = "majestic"

    const val MAGNUM: String = "magnum"

    const val MEDIUM: String = "medium"

    @StringDef(LIGHT, FANCY, MAJESTIC, MAGNUM, MEDIUM)
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(LIGHT, FANCY, MAJESTIC, MAGNUM, MEDIUM)

}

object AvataaarFacialHairColor : DiceBarConfigOptions() {

    const val AUBURN: String = "auburn"

    const val BLACK: String = "black"

    const val BLONDE: String = "blonde"

    const val BROWN: String = "brown"

    const val BLUE: String = "blue"

    const val GRAY: String = "gray"

    const val PASTEL: String = "pastel"

    const val PLATINUM: String = "platinum"

    const val RED: String = "red"

    @StringDef(AUBURN, BLACK, BLONDE, BROWN, BLUE, GRAY, PASTEL, PLATINUM, RED)
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(
        AUBURN, BLACK, BLONDE, BROWN, BLUE, GRAY, PASTEL, PLATINUM, RED
    )
}

object AvataaarClothes : DiceBarConfigOptions() {

    const val BLAZER: String = "blazer"

    const val HOODIE: String = "hoodie"

    const val OVERALL: String = "overall"

    const val SWEATER: String = "sweater"

    const val SHIRT: String = "shirt"

    @StringDef(BLAZER, HOODIE, OVERALL, SWEATER, SHIRT)
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(BLAZER, HOODIE, OVERALL, SWEATER, SHIRT)

}

object AvataaarClothesColor : DiceBarConfigOptions() {

    const val BLACK: String = "black"

    const val BLUE: String = "blue"

    const val GRAY: String = "gray"

    const val HEATHER: String = "heather"

    const val PASTEL: String = "pastel"

    const val PINK: String = "pink"

    const val RED: String = "red"

    const val WHITE: String = "white"

    @StringDef(BLACK, BLUE, GRAY, HEATHER, PASTEL, PINK, RED, WHITE)
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(
        BLACK, BLUE, GRAY, HEATHER, PASTEL, PINK, RED, WHITE
    )

}

object AvataaarEyes : DiceBarConfigOptions() {

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

    @StringDef(
        CLOSE, CRY, DEFAULT, DIZZY, HAPPY, HEARTS, SIDE, SQUIT, SURPRISED, ROLL, WINK, WINK_WACKY
    )
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(
        CLOSE, CRY, DEFAULT, DIZZY, HAPPY, HEARTS, SIDE, SQUIT, SURPRISED, ROLL, WINK, WINK_WACKY
    )
}

object AvataaarEyeBrow : DiceBarConfigOptions() {

    const val ANGRY: String = "angry"

    const val DEFAULT: String = "default"

    const val FLAT: String = "flat"

    const val FROWN: String = "frown"

    const val RAISED: String = "raised"

    const val SAD: String = "sad"

    const val UNIBROW: String = "unibrow"

    const val UP: String = "up"

    @StringDef(ANGRY, DEFAULT, FLAT, FROWN, RAISED, SAD, UNIBROW, UP)
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(
        ANGRY, DEFAULT, FLAT, FROWN, RAISED, SAD, UNIBROW, UP
    )
}

object AvataaarMouth : DiceBarConfigOptions() {

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

    @StringDef(
        CONCERNED, DEFAULT, DISBELIEF, EATING, GRIMACE, SAD,
        SCREAM, SERIOUS, SMILE, TONGUE, TWINKLE, VOMIT
    )
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(
        CONCERNED, DEFAULT, DISBELIEF, EATING, GRIMACE, SAD,
        SCREAM, SERIOUS, SMILE, TONGUE, TWINKLE, VOMIT
    )
}

object AvataaarSkin : DiceBarConfigOptions() {

    const val BLACK: String = "black"

    const val BROWN: String = "brown"

    const val DARK_BROWN: String = "darkBrown"

    const val LIGHT: String = "light"

    const val PALE: String = "pale"

    const val TANNED: String = "tanned"

    const val YELLOW: String = "yellow"


    @StringDef(BLACK, BROWN, DARK_BROWN, LIGHT, PALE, TANNED, YELLOW)
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(
        BLACK, BROWN, DARK_BROWN, LIGHT, PALE, TANNED, YELLOW
    )
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