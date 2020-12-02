package com.beetlestance.aphid.dicebar_kotlin.sprites.female

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfig
import com.squareup.moshi.Json

/**
 * The value of [DiceBarConfig] can be anything you like - but don't use any sensitive or personal data here!
 *
 * @see <a href="https://avatars.dicebear.com/styles/female">Female Config Options</a>
 * */

data class FemaleConfig(

    @FemaleMood.PossibleValues
    @Json(name = FemaleOptions.MOOD)
    val mood: List<String>? = null

) : DiceBarConfig()