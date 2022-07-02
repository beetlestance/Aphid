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

/**
 *  The following options are available for each avatar style.
 *  @see<a href="https://avatars.dicebear.com/docs/options">Config Options.</a>
 * */
interface DiceBarConfig {

    // Avatar border radius
    val radius: Int

    // Fixed width
    val width: Int?

    // Fixed height
    val height: Int?

    // Avatar margin in percent. HTTP-API limitation Max value 25
    val margin: Int

    // Any valid color identifier. HTTP-API limitation Only hex (3-digit, 6-digit and 8-digit)
    // values are allowed. Use url encoded hash: %23.
    val background: String?
}
