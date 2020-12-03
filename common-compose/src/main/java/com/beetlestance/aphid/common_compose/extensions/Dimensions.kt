package com.beetlestance.aphid.common_compose.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AmbientConfiguration
import androidx.compose.ui.platform.AmbientDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun widthPercentage(fraction: Float, excludeRootPadding: Dp): Dp {
    return with(AmbientConfiguration.current) {
        (screenWidthDp * fraction).dp - excludeRootPadding.times(2)
    }
}

@Composable
fun heightPercentage(fraction: Float, excludeRootPadding: Dp): Dp {
    return with(AmbientConfiguration.current) {
        (screenHeightDp * fraction).dp - excludeRootPadding.times(2)
    }
}

@Composable
fun Dp.toPx(): Float = with(AmbientDensity.current) { this@toPx.toPx() }

@Composable
fun Dp.toIntPx(): Int = this.toPx().roundToInt()

@Composable
fun Float.toDp(): Dp = with(AmbientDensity.current) { this@toDp.toDp() }

@Composable
fun Int.toDp(): Dp = this.toFloat().toDp()