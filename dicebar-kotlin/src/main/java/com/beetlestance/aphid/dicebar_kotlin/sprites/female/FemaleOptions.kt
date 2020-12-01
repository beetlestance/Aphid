package com.beetlestance.aphid.dicebar_kotlin.sprites.female

import androidx.annotation.StringDef
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSeedOptions
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSpriteOptions

object FemaleMood : DiceBarSeedOptions() {

    const val HAPPY: String = "happy"

    const val SAD: String = "sad"

    const val SURPRISED: String = "surprised"

    @StringDef(HAPPY, SAD, SURPRISED)
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(HAPPY, SAD, SURPRISED)
}

object FemaleOptions : DiceBarSpriteOptions() {

    private const val MOOD: String = "mood"

    override val spriteSeedValues: List<String> = listOf(MOOD)
}