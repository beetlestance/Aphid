package com.beetlestance.aphid.dicebar_kotlin.sprites.initials

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfig
import com.squareup.moshi.Json

/**
 * The value of [DiceBarConfig] can be anything you like - but don't use any sensitive or personal data here!
 *
 * @see <a href="https://avatars.dicebear.com/styles/initials">Initials Config Options</a>
 * */

data class InitialsConfig(

    @InitialsBackgroundColor.PossibleValues
    @Json(name = InitialsOptions.BACKGROUND_COLORS)
    val backgroundColors: List<String>? = null,

    @InitialsBackgroundColorLevel.PossibleValues
    @Json(name = InitialsOptions.BACKGROUND_COLOR_LEVEL)
    val colorLevel: Int = InitialsBackgroundColorLevel.SIX_HUNDRED,

    // Number between 1 and 100
    @Json(name = InitialsOptions.FONT_SIZE)
    val fontSize: Int = 50,

    // Number between 0 and 2
    @Json(name = InitialsOptions.CHARS)
    val chars: Int = 2,

    @Json(name = InitialsOptions.BOLD)
    val bold: Boolean = false

) : DiceBarConfig()