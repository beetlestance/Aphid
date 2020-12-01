package com.beetlestance.aphid.dicebar_kotlin.sprites.bottts

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfig
import com.squareup.moshi.Json

/**
 * The value of [DiceBarConfig] can be anything you like - but don't use any sensitive or personal data here!
 *
 * @see <a href="https://avatars.dicebear.com/styles/bottts">Bottts Config Options</a>
 * */

data class BotttsConfig(

    @BotttsColor.PossibleValues
    @Json(name = BotttsOptions.COLORS)
    val colors: List<String>? = null,

    @BotttsPrimaryColorLevel.PossibleValues
    @Json(name = BotttsOptions.PRIMARY_COLOR_LEVEL)
    val primaryColorLevel: Int = BotttsPrimaryColorLevel.SIX_HUNDRED,

    @BotttsSecondaryColorLevel.PossibleValues
    @Json(name = BotttsOptions.SECONDARY_COLOR_LEVEL)
    val secondaryColorLevel: Int = BotttsSecondaryColorLevel.FOUR_HUNDRED,

    // Assigns sides and top a random secondary color
    @Json(name = BotttsOptions.COLORFUL)
    val colorful: Boolean = false,

    //Probability in percent avatar will have a mouth
    @Json(name = BotttsOptions.MOUTH_CHANCE)
    val mouthChance: Int = 100,

    // Probability in percent avatar will have side elements
    @Json(name = BotttsOptions.SIDES_CHANCE)
    val sidesChance: Int = 100,

    // Probability in percent avatar will have texture
    @Json(name = BotttsOptions.TEXTURE_CHANCE)
    val textureChance: Int = 50,

    // Probability in percent avatar will have a top element
    @Json(name = BotttsOptions.TOP_CHANCE)
    val topChance: Int = 100

) : DiceBarConfig()