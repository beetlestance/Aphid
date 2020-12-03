package com.beetlestance.aphid.dicebar_kotlin.sprites.female

import androidx.annotation.StringDef
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSpriteOptions

@StringDef(FemaleMood.HAPPY, FemaleMood.SAD, FemaleMood.SURPRISED)
@Retention(AnnotationRetention.SOURCE)
annotation class FemaleMood {
    companion object {

        const val HAPPY: String = "happy"

        const val SAD: String = "sad"

        const val SURPRISED: String = "surprised"

        val possibleValues: List<String> = listOf(HAPPY, SAD, SURPRISED)
    }
}

object FemaleOptions : DiceBarSpriteOptions() {

    const val MOOD: String = "mood"

    override val spriteConfigOptions: List<String> = listOf(MOOD)
}