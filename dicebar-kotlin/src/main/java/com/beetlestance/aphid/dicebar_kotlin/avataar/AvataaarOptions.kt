package com.beetlestance.aphid.dicebar_kotlin.avataar

import androidx.annotation.StringDef
import com.beetlestance.aphid.dicebar_kotlin.DiceBarSeedOptions
import com.beetlestance.aphid.dicebar_kotlin.DiceBarSpriteOptions

object AvataaarStyle : DiceBarSeedOptions() {

    const val CIRCLE: String = "circle"

    const val TRANSPARENT: String = "transparent"

    @StringDef(CIRCLE, TRANSPARENT)
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(CIRCLE, TRANSPARENT)
}


object AvataaarMode : DiceBarSeedOptions() {

    const val INCLUDE: String = "include"

    const val EXCLUDE: String = "exclude"

    @StringDef(INCLUDE, EXCLUDE)
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(INCLUDE, EXCLUDE)
}

object AvataaarTop : DiceBarSeedOptions() {

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

object AvataaarHat : DiceBarSeedOptions() {

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

object AvataaarHairColor : DiceBarSeedOptions() {

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

object AvataaarAccessories : DiceBarSeedOptions() {

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

object AvataaarAccessoriesColor : DiceBarSeedOptions() {

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

object AvataaarFacialHair : DiceBarSeedOptions() {

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

object AvataaarFacialHairColor : DiceBarSeedOptions() {

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

object AvataaarClothes : DiceBarSeedOptions() {

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

object AvataaarClothesColor : DiceBarSeedOptions() {

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

object AvataaarEyes : DiceBarSeedOptions() {

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

object AvataaarEyeBrow : DiceBarSeedOptions() {

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

object AvataaarMouth : DiceBarSeedOptions() {

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

object AvataaarSkin : DiceBarSeedOptions() {

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

    private const val ACCESSORIES: String = "accessories"

    private const val ACCESSORIES_CHANCE: String = "accessoriesChance"

    private const val ACCESSORIES_COLOR: String = "accessoriesColor"

    private const val CLOTHES: String = "clothes"

    private const val CLOTHES_COLOR: String = "clothesColor"

    private const val EYES: String = "eyes"

    private const val EYEBROW: String = "eyebrow"

    private const val FACIAL_HAIR: String = "facialHair"

    private const val FACIAL_HAIR_CHANCE: String = "facialHairChance"

    private const val FACIAL_HAIR_COLOR: String = "facialHairColor"

    private const val HAT_COLOR: String = "hatColor"

    private const val HAIR_COLOR: String = "hairColor"

    private const val MODE: String = "mode"

    private const val MOUTH: String = "mouth"

    private const val SKIN: String = "skin"

    private const val STYLE: String = "style"

    private const val TOP: String = "top"

    private const val TOP_CHANCE: String = "topChance"

    override val spriteSeedValues: List<String> = listOf(
        ACCESSORIES, ACCESSORIES_CHANCE, ACCESSORIES_COLOR, CLOTHES, CLOTHES_COLOR, EYES,
        EYEBROW, FACIAL_HAIR, FACIAL_HAIR_CHANCE, FACIAL_HAIR_COLOR, HAT_COLOR, HAIR_COLOR,
        MODE, MOUTH, SKIN, STYLE, TOP, TOP_CHANCE
    )
} 