package com.beetlestance.aphid

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ConfigurationAmbient
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun widthPercentage(fraction: Float, excludeRootPadding: Dp): Dp {
    return (ConfigurationAmbient.current.screenWidthDp * fraction).dp - excludeRootPadding.times(2)
}

@Composable
fun heightPercentage(fraction: Float, excludeRootPadding: Dp): Dp {
    return (ConfigurationAmbient.current.screenHeightDp * fraction).dp - excludeRootPadding.times(2)
}
