package com.beetlestance.aphid.dicebar_kotlin.sprites.human

import com.beetlestance.aphid.dicebar_kotlin.DiceBarAvatar
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSprite

class HumanSprite : DiceBarSprite() {

    override val type: String = DiceBarAvatar.HUMAN

    override val options: List<String> = HumanOptions.possibleConfigOptions
}