package com.beetlestance.aphid.dicebar_kotlin.sprites.male

import com.beetlestance.aphid.dicebar_kotlin.DiceBarAvatar
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSprite

class MaleSprite : DiceBarSprite() {

    override val type: String = DiceBarAvatar.MALE

    override val options: List<String> = MaleOptions.possibleConfigOptions
}