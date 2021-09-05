package com.beetlestance.aphid.common_compose.theme

import androidx.compose.runtime.Composable

enum class AphidThemeStyles {
    ORIGINAL
}

@Composable
fun AphidTheme(
    themeStyles: AphidThemeStyles = AphidThemeStyles.ORIGINAL,
    content: @Composable () -> Unit
) {
    ProvideAphidColors(themeStyles = themeStyles, content = content)
}

object AphidTheme {
    val colors: AphidColors
        @Composable
        get() = LocalAphidColors.current
}