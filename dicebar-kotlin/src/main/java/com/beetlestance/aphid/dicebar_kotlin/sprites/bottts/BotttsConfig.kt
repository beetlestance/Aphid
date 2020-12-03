package com.beetlestance.aphid.dicebar_kotlin.sprites.bottts

import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfig
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSpriteOptions
import com.squareup.moshi.Json

/**
 * The value of [DiceBarConfig] can be anything you like - but don't use any sensitive or personal data here!
 *
 * @see <a href="https://avatars.dicebear.com/styles/bottts">Bottts Config Options</a>
 * */

data class BotttsConfig(

    @BotttsColor
    @Json(name = BotttsOptions.COLORS)
    val colors: List<String>? = null,

    @BotttsPrimaryColorLevel
    @Json(name = BotttsOptions.PRIMARY_COLOR_LEVEL)
    val primaryColorLevel: Int = BotttsPrimaryColorLevel.SIX_HUNDRED,

    @BotttsSecondaryColorLevel
    @Json(name = BotttsOptions.SECONDARY_COLOR_LEVEL)
    val secondaryColorLevel: Int = BotttsSecondaryColorLevel.FOUR_HUNDRED,

    // Assigns sides and top a random secondary color
    @Json(name = BotttsOptions.COLORFUL)
    val colorful: Boolean = false,

    //Probability in percent avatar will have a mouth
    @FloatRange(from = 0.toDouble(), to = 100.toDouble())
    @Json(name = BotttsOptions.MOUTH_CHANCE)
    val mouthChance: Float = 100f,

    // Probability in percent avatar will have side elements
    @FloatRange(from = 0.toDouble(), to = 100.toDouble())
    @Json(name = BotttsOptions.SIDES_CHANCE)
    val sidesChance: Float = 100f,

    // Probability in percent avatar will have texture
    @FloatRange(from = 0.toDouble(), to = 100.toDouble())
    @Json(name = BotttsOptions.TEXTURE_CHANCE)
    val textureChance: Float = 50f,

    // Probability in percent avatar will have a top element
    @FloatRange(from = 0.toDouble(), to = 100.toDouble())
    @Json(name = BotttsOptions.TOP_CHANCE)
    val topChance: Float = 100f,

    @Json(name = DiceBarSpriteOptions.RADIUS)
    override val radius: Int = 0,

    // Fixed width
    @Json(name = DiceBarSpriteOptions.WIDTH)
    override val width: Int? = null,

    // Fixed height
    @Json(name = DiceBarSpriteOptions.HEIGHT)
    override val height: Int? = null,

    // Avatar margin in percent. HTTP-API limitation Max value 25
    @IntRange(from = 0, to = 25)
    @Json(name = DiceBarSpriteOptions.MARGIN)
    override val margin: Int = 0,

    // Any valid color identifier. HTTP-API limitation Only hex (3-digit, 6-digit and 8-digit)
    // values are allowed. Use url encoded hash: %23.
    @Json(name = DiceBarSpriteOptions.BACKGROUND)
    override val background: String? = null

) : DiceBarConfig