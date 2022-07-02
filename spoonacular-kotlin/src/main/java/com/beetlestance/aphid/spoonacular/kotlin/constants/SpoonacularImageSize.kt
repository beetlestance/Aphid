/*
 * Copyright 2020 BeetleStance
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
package com.beetlestance.aphid.spoonacular.kotlin.constants

@Suppress("unused")
object SpoonacularImageSize {

    object Equipment {
        const val LOW_QUALITY: String = "100x100"
        const val MEDIUM_QUALITY: String = "250x250"
        const val HIGH_QUALITY: String = "500x500"
    }

    object Grocery {
        const val LOW_QUALITY: String = "90x90"
        const val MEDIUM_QUALITY: String = "312x231"
        const val HIGH_QUALITY: String = "636x393"
    }

    object Ingredient {
        const val LOW_QUALITY: String = "100x100"
        const val MEDIUM_QUALITY: String = "250x250"
        const val HIGH_QUALITY: String = "500x500"
    }

    object MenuItem {
        const val LOW_QUALITY: String = "90x90"
        const val MEDIUM_QUALITY: String = "312x231"
        const val HIGH_QUALITY: String = "636x393"
    }

    object Recipe {
        const val ULTRA_LOW_QUALITY: String = "90x90"
        const val VERY_LOW_QUALITY: String = "240x150"
        const val LOW_QUALITY: String = "312x150"
        const val MEDIUM_QUALITY: String = "312x231"
        const val HIGH_QUALITY: String = "480x360"
        const val VERY_HIGH_QUALITY: String = "556x370"
        const val ULTRA_HIGH_QUALITY: String = "636x393"
    }
}
