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
package com.beetlestance.aphid.dicebar_kotlin.sprites.male

import androidx.annotation.StringDef
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSpriteOptions

@StringDef(MaleMood.HAPPY, MaleMood.SAD, MaleMood.SURPRISED)
@Retention(AnnotationRetention.SOURCE)
annotation class MaleMood {
    companion object {

        const val HAPPY: String = "happy"

        const val SAD: String = "sad"

        const val SURPRISED: String = "surprised"

        val possibleValues: List<String> = listOf(HAPPY, SAD, SURPRISED)
    }
}

object MaleOptions : DiceBarSpriteOptions() {

    const val MOOD: String = "mood"

    override val spriteConfigOptions: List<String> = listOf(MOOD)
}
