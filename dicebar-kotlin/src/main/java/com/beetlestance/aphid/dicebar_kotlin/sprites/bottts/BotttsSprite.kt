package com.beetlestance.aphid.dicebar_kotlin.sprites.bottts

import com.beetlestance.aphid.dicebar_kotlin.DiceBarAvatar
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSprite


class BotttsSprite : DiceBarSprite() {

    override val type: String = DiceBarAvatar.BOTTTS

    override val options: List<String> = BotttsOptions.possibleSeedValues
}