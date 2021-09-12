package com.beetlestance.aphid.common_compose.theme

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