package com.beetlestance.aphid.dicebar_kotlin.sprites.male

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfig
import com.squareup.moshi.Json

/**
 * The value of [DiceBarConfig] can be anything you like - but don't use any sensitive or personal data here!
 *
 * @see <a href="https://avatars.dicebear.com/styles/male">Male Config Options</a>
 * */

data class MaleConfig(

    @MaleMood.PossibleValues
    @Json(name = MaleOptions.MOOD)
    val mood: List<String>? = null

) : DiceBarConfig()