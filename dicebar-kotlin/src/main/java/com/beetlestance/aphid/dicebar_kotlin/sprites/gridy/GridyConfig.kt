package com.beetlestance.aphid.dicebar_kotlin.sprites.gridy

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfig
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
    val deterministic: Boolean = false

) : DiceBarConfig()