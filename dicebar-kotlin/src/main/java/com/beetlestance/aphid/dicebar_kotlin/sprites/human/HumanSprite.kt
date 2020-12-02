package com.beetlestance.aphid.dicebar_kotlin.sprites.human

import com.beetlestance.aphid.dicebar_kotlin.DiceBarAvatars
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSprite

class HumanSprite : DiceBarSprite() {

    override val type: String = DiceBarAvatars.HUMAN

    override val options: List<String> = HumanOptions.possibleConfigOptions
}