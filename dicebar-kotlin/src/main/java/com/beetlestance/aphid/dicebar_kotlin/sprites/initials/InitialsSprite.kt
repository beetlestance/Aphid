package com.beetlestance.aphid.dicebar_kotlin.sprites.initials

import com.beetlestance.aphid.dicebar_kotlin.DiceBarAvatars
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSprite


class InitialsSprite : DiceBarSprite() {

    override val type: String = DiceBarAvatars.INITIALS

    override val options: List<String> = InitialsOptions.possibleConfigOptions
}