package com.beetlestance.aphid.dicebar_kotlin.sprites.female

import com.beetlestance.aphid.dicebar_kotlin.DiceBarAvatars
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSprite

class FemaleSprite : DiceBarSprite() {

    override val type: String = DiceBarAvatars.FEMALE

    override val options: List<String> = FemaleOptions.possibleConfigOptions
}