package com.beetlestance.aphid.common_compose.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember


@Composable
fun AphidTheme(
    themeStyles: AphidThemeStyles = AphidThemeStyles.ORIGINAL,
    content: @Composable () -> Unit
) {
    val colors = when (themeStyles) {
        AphidThemeStyles.ORIGINAL -> OriginalColorPalette
    }

    val typography = when (themeStyles) {
        AphidThemeStyles.ORIGINAL -> AphidTypography()
    }

    val shapes = when (themeStyles) {
        AphidThemeStyles.ORIGINAL -> AphidShapes()
    }

    val rememberedColors = remember {
        // Explicitly creating a new object here so we don't mutate the initial [colors]
        // provided, and overwrite the values set in it.
        colors.copy()
    }.apply {
        update(colors)
    }

    CompositionLocalProvider(
        LocalAphidColors provides rememberedColors,
        LocalAphidTypography provides typography,
        LocalAphidShapes provides shapes
    ) {
        MaterialTheme(
            colors = AphidTheme.colors.materialColors(),
            typography = AphidTheme.typography.materialTypography(),
            shapes = AphidTheme.shapes.materialShapes(),
            content = content
        )
    }
}

object AphidTheme {
    val colors: AphidColors
        @Composable
        get() = LocalAphidColors.current

    val typography: AphidTypography
        @Composable
        get() = LocalAphidTypography.current

    val shapes: AphidShapes
        @Composable
        get() = LocalAphidShapes.current
}

enum class AphidThemeStyles {
    ORIGINAL
}