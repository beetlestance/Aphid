package com.beetlestance.aphid.dicebar_kotlin.sprites.avataar

import com.beetlestance.aphid.dicebar_kotlin.DiceBarAvatars
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSprite

class AvataaarsSprite : DiceBarSprite() {

    override val type: String = DiceBarAvatars.AVATAAARS

    override val options: List<String> = AvataaarOptions.possibleConfigOptions
}