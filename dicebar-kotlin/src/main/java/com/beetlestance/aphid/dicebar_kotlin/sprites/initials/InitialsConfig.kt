package com.beetlestance.aphid.dicebar_kotlin.sprites.initials

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfig
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSpriteOptions
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
    val bold: Boolean = false,

    @Json(name = DiceBarSpriteOptions.RADIUS)
    override val radius: Int = 0,

    // Fixed width
    @Json(name = DiceBarSpriteOptions.WIDTH)
    override val width: Int? = null,

    // Fixed height
    @Json(name = DiceBarSpriteOptions.HEIGHT)
    override val height: Int? = null,

    // Avatar margin in percent. HTTP-API limitation Max value 25
    @Json(name = DiceBarSpriteOptions.MARGIN)
    override val margin: Int = 0,

    // Any valid color identifier. HTTP-API limitation Only hex (3-digit, 6-digit and 8-digit)
    // values are allowed. Use url encoded hash: %23.
    @Json(name = DiceBarSpriteOptions.BACKGROUND)
    override val background: String? = null

) : DiceBarConfig