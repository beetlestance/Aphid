/*
 * Copyright 2021 BeetleStance
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beetlestance.aphid.dicebar.kotlin.sprites.male

import androidx.annotation.IntRange
import com.beetlestance.aphid.dicebar.kotlin.sprites.DiceBarConfig
import com.beetlestance.aphid.dicebar.kotlin.sprites.DiceBarSpriteOptions
import com.squareup.moshi.Json

/**
 * The value of [DiceBarConfig] can be anything you like - but don't use any sensitive or personal data here!
 *
 * @see <a href="https://avatars.dicebear.com/styles/male">Male Config Options</a>
 * */

data class MaleConfig(

    @MaleMood
    @Json(name = MaleOptions.MOOD)
    val mood: List<String>? = null,

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
