package com.beetlestance.aphid.dicebar_kotlin.sprites.gridy

import com.beetlestance.aphid.dicebar_kotlin.DiceBarAvatars
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSprite

class GridySprite : DiceBarSprite() {

    override val type: String = DiceBarAvatars.GRIDY

    override val options: List<String> = GridyOptions.possibleConfigOptions
}