package com.beetlestance.aphid.dicebar_kotlin.sprites.male

import androidx.annotation.StringDef
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSeedOptions
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSpriteOptions

object MaleMood : DiceBarSeedOptions() {

    const val HAPPY: String = "happy"

    const val SAD: String = "sad"

    const val SURPRISED: String = "surprised"

    @StringDef(HAPPY, SAD, SURPRISED)
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    override val possibleValues: List<String> = listOf(HAPPY, SAD, SURPRISED)
}

object MaleOptions : DiceBarSpriteOptions() {

    private const val MOOD: String = "mood"

    override val spriteSeedValues: List<String> = listOf(MOOD)
}