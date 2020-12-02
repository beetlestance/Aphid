package com.beetlestance.aphid.dicebar_kotlin.sprites.bottts

import com.beetlestance.aphid.dicebar_kotlin.DiceBarAvatars
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSprite


class BotttsSprite : DiceBarSprite() {

    override val type: String = DiceBarAvatars.BOTTTS

    override val options: List<String> = BotttsOptions.possibleConfigOptions
}