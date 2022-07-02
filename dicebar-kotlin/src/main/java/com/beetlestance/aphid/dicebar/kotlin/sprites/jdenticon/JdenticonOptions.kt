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
package com.beetlestance.aphid.dicebar.kotlin.sprites.jdenticon

import com.beetlestance.aphid.dicebar.kotlin.sprites.DiceBarSpriteOptions

object JdenticonOptions : DiceBarSpriteOptions() {

    const val COLOR_LIGHTNESS: String = "colorLightness"

    const val COLOR_SATURATION: String = "colorSaturation"

    const val GRAYSCALE_LIGHTNESS: String = "grayscaleLightness"

    const val GRAYSCALE_SATURATION: String = "grayscaleSaturation"

    const val HUES: String = "hues"

    override val spriteConfigOptions: List<String> = listOf(
        COLOR_LIGHTNESS,
        COLOR_SATURATION,
        GRAYSCALE_LIGHTNESS,
        GRAYSCALE_SATURATION,
        HUES
    )
}
