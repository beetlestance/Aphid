package com.beetlestance.aphid.common_compose.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ConfigurationAmbient
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun widthPercentage(fraction: Float, excludeRootPadding: Dp): Dp {
    return with(ConfigurationAmbient.current) {
        (screenWidthDp * fraction).dp - excludeRootPadding.times(2)
    }
    //return (ConfigurationAmbient.current.screenWidthDp * fraction).dp - excludeRootPadding.times(2)
}

@Composable
fun heightPercentage(fraction: Float, excludeRootPadding: Dp): Dp {
    return (ConfigurationAmbient.current.screenHeightDp * fraction).dp - excludeRootPadding.times(2)
}

@Composable
fun Dp.toPx(): Float = with(DensityAmbient.current) { this@toPx.toPx() }

@Composable
fun Dp.toIntPx() = this.toPx().roundToInt()

@Composable
fun Float.toDp(): Dp = with(DensityAmbient.current) { this@toDp.toDp() }

@Composable
fun Int.toDp(): Dp = this.toFloat().toDp()