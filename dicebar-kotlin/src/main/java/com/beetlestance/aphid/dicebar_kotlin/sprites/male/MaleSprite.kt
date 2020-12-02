package com.beetlestance.aphid.dicebar_kotlin.sprites.male

import com.beetlestance.aphid.dicebar_kotlin.DiceBarAvatars
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSprite

class MaleSprite : DiceBarSprite() {

    override val type: String = DiceBarAvatars.MALE

    override val options: List<String> = MaleOptions.possibleConfigOptions
}