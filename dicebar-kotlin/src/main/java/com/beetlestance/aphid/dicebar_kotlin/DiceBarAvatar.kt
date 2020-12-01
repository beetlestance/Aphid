package com.beetlestance.aphid.dicebar_kotlin

import androidx.annotation.StringDef

object DiceBarAvatar {

    const val AVATAAARS: String = "avataaars"

    const val BOTTTS: String = "bottts"

    const val FEMALE: String = "female"

    const val GRIDY: String = "gridy"

    const val HUMAN: String = "human"

    const val IDENTICON: String = "identicon"

    const val INITIALS: String = "initials"

    const val JDENTICON: String = "jdenticon"

    const val MALE: String = "male"

    @StringDef(AVATAAARS, BOTTTS, FEMALE, GRIDY, HUMAN, IDENTICON, INITIALS, JDENTICON, MALE)
    @Retention(AnnotationRetention.SOURCE)
    annotation class PossibleValues

    val possibleValues: List<String> = listOf(
        AVATAAARS, BOTTTS, FEMALE, GRIDY, HUMAN, IDENTICON, INITIALS, JDENTICON, MALE
    )
}
