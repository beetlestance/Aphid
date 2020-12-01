package com.beetlestance.aphid.dicebar_kotlin.sprites.human

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfig
import com.squareup.moshi.Json


/**
 * The value of [DiceBarConfig] can be anything you like - but don't use any sensitive or personal data here!
 *
 * @see <a href="https://avatars.dicebear.com/styles/human">Human Config Options</a>
 * */

data class HumanConfig(

    @HumanMood.PossibleValues
    @Json(name = HumanOptions.MOOD)
    val mood: List<String>? = null,

    ) : DiceBarConfig()