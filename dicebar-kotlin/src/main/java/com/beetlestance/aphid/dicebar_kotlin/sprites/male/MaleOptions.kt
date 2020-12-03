package com.beetlestance.aphid.dicebar_kotlin.sprites.male

import androidx.annotation.StringDef
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSpriteOptions

@StringDef(MaleMood.HAPPY, MaleMood.SAD, MaleMood.SURPRISED)
@Retention(AnnotationRetention.SOURCE)
annotation class MaleMood {
    companion object {

        const val HAPPY: String = "happy"

        const val SAD: String = "sad"

        const val SURPRISED: String = "surprised"

        val possibleValues: List<String> = listOf(HAPPY, SAD, SURPRISED)
    }
}

object MaleOptions : DiceBarSpriteOptions() {

    const val MOOD: String = "mood"

    override val spriteConfigOptions: List<String> = listOf(MOOD)
}