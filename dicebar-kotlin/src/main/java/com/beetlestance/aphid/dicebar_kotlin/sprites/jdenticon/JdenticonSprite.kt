package com.beetlestance.aphid.dicebar_kotlin.sprites.jdenticon

import com.beetlestance.aphid.dicebar_kotlin.DiceBarAvatars
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSprite


class JdenticonSprite : DiceBarSprite() {

    override val type: String = DiceBarAvatars.JDENTICON

    override val options: List<String> = JdenticonOptions.possibleConfigOptions
}