package com.beetlestance.aphid.common_compose.theme

import androidx.compose.material.Colors
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse


val DeepOrange200 = Color(0xFFF8673E)
val Amber500 = Color(0xffFEC813)
val Purple200 = Color(0xffbb86fc)
val Green200 = Color(0xff52DE97)
val Teal700 = Color(0xFF018786)
val White = Color(0xffffffff)
val Black = Color(0xff000000)
val Black800 = Color(0xff121212)
val Grey400_A30 = Color(0x4DC4C4C4)
val Grey700 = Color(0xff696969)
val GunPowder = Color(0xff3F3D56)
val Red600 = Color(0xffb00020)
val Red200 = Color(0xffcf6679)

val OriginalColorPalette = AphidColors(
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
    onError = White,
    themeStyle = AphidThemeStyles.ORIGINAL
)

@Stable
class AphidColors(
    primary: Color,
    primaryVariant: Color,
    secondary: Color,
    secondaryVariant: Color,
    background: Color,
    surface: Color,
    error: Color,
    onPrimary: Color,
    onSecondary: Color,
    onBackground: Color,
    onSurface: Color,
    onError: Color,
    themeStyle: AphidThemeStyles
) {
    var primary by mutableStateOf(primary)
        private set
    var primaryVariant by mutableStateOf(primaryVariant)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var secondaryVariant by mutableStateOf(secondaryVariant)
        private set
    var background by mutableStateOf(background)
        private set
    var surface by mutableStateOf(surface)
        private set
    var error by mutableStateOf(error)
        private set
    var onPrimary by mutableStateOf(onPrimary)
        private set
    var onSecondary by mutableStateOf(onSecondary)
        private set
    var onBackground by mutableStateOf(onBackground)
        private set
    var onSurface by mutableStateOf(onSurface)
        private set
    var onError by mutableStateOf(onError)
        private set
    var themeStyle by mutableStateOf(themeStyle)
        private set


    private fun darkTheme() = themeStyle != AphidThemeStyles.ORIGINAL

    fun update(other: AphidColors) {
        primary = other.primary
        primaryVariant = other.primaryVariant
        secondary = other.secondary
        secondaryVariant = other.secondaryVariant
        background = other.background
        surface = other.surface
        error = other.error
        onPrimary = other.onPrimary
        onSecondary = other.onSecondary
        onBackground = other.onBackground
        onSurface = other.onSurface
        onError = other.onError
        themeStyle = other.themeStyle
    }

    fun copy(): AphidColors = AphidColors(
        primary = primary,
        primaryVariant = primaryVariant,
        secondary = secondary,
        secondaryVariant = secondaryVariant,
        background = background,
        surface = surface,
        error = error,
        onPrimary = onPrimary,
        onSecondary = onSecondary,
        onBackground = onBackground,
        onSurface = onSurface,
        onError = onError,
        themeStyle = themeStyle
    )

    fun materialColors() = Colors(
        primary = primary,
        primaryVariant = primaryVariant,
        secondary = secondary,
        secondaryVariant = secondaryVariant,
        background = background,
        surface = surface,
        error = error,
        onPrimary = onPrimary,
        onSecondary = onSecondary,
        onBackground = onBackground,
        onSurface = onSurface,
        onError = onError,
        isLight = darkTheme()
    )
}

fun AphidColors.contentColorFor(backgroundColor: Color): Color {
    return when (backgroundColor) {
        primary -> onPrimary
        primaryVariant -> onPrimary
        secondary -> onSecondary
        secondaryVariant -> onSecondary
        background -> onBackground
        surface -> onSurface
        error -> onError
        else -> Color.Unspecified
    }
}

@Composable
fun contentColorFor(backgroundColor: Color): Color {
    return AphidTheme.colors.contentColorFor(backgroundColor)
        .takeOrElse { LocalContentColor.current }
}

val LocalAphidColors = staticCompositionLocalOf<AphidColors> {
    error("No AphidColors provided")
}
