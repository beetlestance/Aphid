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
package com.beetlestance.aphid.common_compose.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun widthPercentage(fraction: Float, excludeRootPadding: Dp): Dp {
    return with(LocalConfiguration.current) {
        (screenWidthDp * fraction).dp - excludeRootPadding.times(2)
    }
}

@Composable
fun heightPercentage(fraction: Float, excludeRootPadding: Dp): Dp {
    return with(LocalConfiguration.current) {
        (screenHeightDp * fraction).dp - excludeRootPadding.times(2)
    }
}

@Composable
fun Dp.toPx(): Float = with(LocalDensity.current) { this@toPx.toPx() }

@Composable
fun Dp.toIntPx(): Int = this.toPx().toInt()

fun Dp.toPx(density: Density): Float = with(density) { this@toPx.toPx() }

fun Dp.toIntPx(density: Density): Int = this.toPx(density).toInt()
