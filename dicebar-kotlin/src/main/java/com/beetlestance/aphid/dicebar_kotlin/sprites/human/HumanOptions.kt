package com.beetlestance.aphid.dicebar_kotlin.sprites.human

import androidx.annotation.StringDef
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSpriteOptions

@StringDef(HumanMood.HAPPY, HumanMood.SAD, HumanMood.SURPRISED)
@Retention(AnnotationRetention.SOURCE)
annotation class HumanMood {

    companion object {
        const val HAPPY: String = "happy"

        const val SAD: String = "sad"

        const val SURPRISED: String = "surprised"

        val possibleValues: List<String> = listOf(HAPPY, SAD, SURPRISED)
    }
}

object HumanOptions : DiceBarSpriteOptions() {

    const val MOOD: String = "mood"

    override val spriteConfigOptions: List<String> = listOf(MOOD)
}