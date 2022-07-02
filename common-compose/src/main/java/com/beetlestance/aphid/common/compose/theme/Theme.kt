/*
 * Copyright 2022 BeetleStance
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
package com.beetlestance.aphid.common.compose.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

val originalColors = lightColors(
    primary = DeepOrange200,
    primaryVariant = Amber500,
    secondary = Green200,
    secondaryVariant = Teal700,
    background = White,
    surface = White,
    error = Red600,
    onPrimary = White,
    onSecondary = White,
    onSurface = Black,
    onBackground = Black,
    onError = White
)

@Composable
fun AphidTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = originalColors,
        content = content
    )
}
