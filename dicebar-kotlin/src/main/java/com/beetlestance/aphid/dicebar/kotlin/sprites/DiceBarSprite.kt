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
package com.beetlestance.aphid.dicebar.kotlin.sprites

import com.beetlestance.aphid.dicebar.kotlin.DiceBarAvatars

abstract class DiceBarSprite {

    @DiceBarAvatars
    abstract val type: String

    abstract val options: List<String>
}

abstract class DiceBarSpriteOptions {

    protected abstract val spriteConfigOptions: List<String>

    val possibleConfigOptions: List<String> by lazy { globalConfigOptions + spriteConfigOptions }

    companion object {

        const val RADIUS: String = "radius"

        const val WIDTH: String = "width"

        const val HEIGHT: String = "height"

        const val MARGIN: String = "margin"

        const val BACKGROUND: String = "background"

        val globalConfigOptions: List<String> = listOf(
            RADIUS,
            WIDTH,
            HEIGHT,
            MARGIN,
            BACKGROUND
        )
    }
}
