package com.beetlestance.aphid.dicebar_kotlin.sprites.gridy

import androidx.annotation.IntRange
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfig
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSpriteOptions
import com.squareup.moshi.Json

/**
 * The value of [DiceBarConfig] can be anything you like - but don't use any sensitive or personal data here!
 *
 * @see <a href="https://avatars.dicebear.com/styles/gridy">Gridy Config Options</a>
 * */

data class GridyConfig(

    // Use different colors for eyes and mouth
    @Json(name = GridyOptions.COLORFUL)
    val colorful: Boolean = false,
    /**
     * Force deterministic output
     * @see <a href="https://github.com/DiceBear/avatars/issues/64">Force deterministic output</a>
     */
    @Json(name = GridyOptions.DETERMINISTIC)
    val deterministic: Boolean = false,

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