package com.beetlestance.aphid.dicebar_kotlin.sprites.avataar

import com.beetlestance.aphid.dicebar_kotlin.DiceBarAvatar
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSprite

class AvataaarsSprite : DiceBarSprite() {

    override val type: String = DiceBarAvatar.AVATAAARS

    override val options: List<String> = AvataaarOptions.possibleConfigOptions
}