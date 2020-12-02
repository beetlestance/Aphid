package com.beetlestance.aphid.dicebar_kotlin.sprites.identicon

import com.beetlestance.aphid.dicebar_kotlin.DiceBarAvatars
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSprite


class IdenticonSprite : DiceBarSprite() {

    override val type: String = DiceBarAvatars.BOTTTS

    override val options: List<String> = IdenticonOptions.possibleConfigOptions
}