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
package com.beetlestance.aphid.dicebar_kotlin

import androidx.annotation.StringDef

/**
 * Do you want to create male, female or abstract avatars? You have the choice between several
 * lovely designed avatar styles.
 *
 * @see <a href="https://avatars.dicebear.com/styles">DiceBar Styles</a>
 * */
@StringDef(
    DiceBarAvatars.AVATAAARS,
    DiceBarAvatars.BOTTTS,
    DiceBarAvatars.FEMALE,
    DiceBarAvatars.GRIDY,
    DiceBarAvatars.HUMAN,
    DiceBarAvatars.IDENTICON,
    DiceBarAvatars.INITIALS,
    DiceBarAvatars.JDENTICON,
    DiceBarAvatars.MALE
)
@Retention(AnnotationRetention.SOURCE)
annotation class DiceBarAvatars {
    companion object {

        const val AVATAAARS: String = "avataaars"

        const val BOTTTS: String = "bottts"

        const val FEMALE: String = "female"

        const val GRIDY: String = "gridy"

        const val HUMAN: String = "human"

        const val IDENTICON: String = "identicon"

        const val INITIALS: String = "initials"

        const val JDENTICON: String = "jdenticon"

        const val MALE: String = "male"

        val possibleValues: List<String> = listOf(
            AVATAAARS, BOTTTS, FEMALE, GRIDY, HUMAN, IDENTICON, INITIALS, JDENTICON, MALE
        )
    }
}
