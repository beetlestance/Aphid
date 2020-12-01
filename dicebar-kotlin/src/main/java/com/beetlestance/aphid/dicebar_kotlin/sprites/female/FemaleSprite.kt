package com.beetlestance.aphid.dicebar_kotlin.sprites.female

import com.beetlestance.aphid.dicebar_kotlin.DiceBarAvatar
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSprite

class FemaleSprite : DiceBarSprite() {

    override val type: String = DiceBarAvatar.FEMALE

    override val options: List<String> = FemaleOptions.possibleSeedValues
}