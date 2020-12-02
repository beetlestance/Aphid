package com.beetlestance.aphid.dicebar_kotlin.sprites.identicon

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfig
import com.squareup.moshi.Json

/**
 * The value of [DiceBarConfig] can be anything you like - but don't use any sensitive or personal data here!
 *
 * @see <a href="https://avatars.dicebear.com/styles/identicon">Identicon Config Options</a>
 * */

data class IdenticonConfig(

    @IdenticonColor.PossibleValues
    @Json(name = IdenticonOptions.COLORS)
    val colors: List<String>? = null,

    @IdenticonColorLevel.PossibleValues
    @Json(name = IdenticonOptions.COLOR_LEVEL)
    val colorLevel: Int = IdenticonColorLevel.SIX_HUNDRED

) : DiceBarConfig()