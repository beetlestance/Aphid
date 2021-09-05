package com.beetlestance.aphid.common_compose.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
class AphidTypography internal constructor(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val h6: TextStyle,
    val subtitle1: TextStyle,
    val subtitle2: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val button: TextStyle,
    val caption: TextStyle,
    val overline: TextStyle
) {

    constructor(
        defaultFontFamily: FontFamily = FontFamily.Default,
        h1: TextStyle = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 96.sp,
            letterSpacing = (-1.5).sp
        ),
        h2: TextStyle = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 60.sp,
            letterSpacing = (-0.5).sp
        ),
        h3: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 48.sp,
            letterSpacing = 0.sp
        ),
        h4: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 34.sp,
            letterSpacing = 0.25.sp
        ),
        h5: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            letterSpacing = 0.sp
        ),
        h6: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            letterSpacing = 0.15.sp
        ),
        subtitle1: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.15.sp
        ),
        subtitle2: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 0.1.sp
        ),
        body1: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.5.sp
        ),
        body2: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.25.sp
        ),
        button: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 1.25.sp
        ),
        caption: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            letterSpacing = 0.4.sp
        ),
        overline: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            letterSpacing = 1.5.sp
        )
    ) : this(
        h1 = h1.withDefaultFontFamily(defaultFontFamily),
        h2 = h2.withDefaultFontFamily(defaultFontFamily),
        h3 = h3.withDefaultFontFamily(defaultFontFamily),
        h4 = h4.withDefaultFontFamily(defaultFontFamily),
        h5 = h5.withDefaultFontFamily(defaultFontFamily),
        h6 = h6.withDefaultFontFamily(defaultFontFamily),
        subtitle1 = subtitle1.withDefaultFontFamily(defaultFontFamily),
        subtitle2 = subtitle2.withDefaultFontFamily(defaultFontFamily),
        body1 = body1.withDefaultFontFamily(defaultFontFamily),
        body2 = body2.withDefaultFontFamily(defaultFontFamily),
        button = button.withDefaultFontFamily(defaultFontFamily),
        caption = caption.withDefaultFontFamily(defaultFontFamily),
        overline = overline.withDefaultFontFamily(defaultFontFamily)
    )

    fun copy(
        h1: TextStyle = this.h1,
        h2: TextStyle = this.h2,
        h3: TextStyle = this.h3,
        h4: TextStyle = this.h4,
        h5: TextStyle = this.h5,
        h6: TextStyle = this.h6,
        subtitle1: TextStyle = this.subtitle1,
        subtitle2: TextStyle = this.subtitle2,
        body1: TextStyle = this.body1,
        body2: TextStyle = this.body2,
        button: TextStyle = this.button,
        caption: TextStyle = this.caption,
        overline: TextStyle = this.overline
    ): Typography = Typography(
        h1 = h1,
        h2 = h2,
        h3 = h3,
        h4 = h4,
        h5 = h5,
        h6 = h6,
        subtitle1 = subtitle1,
        subtitle2 = subtitle2,
        body1 = body1,
        body2 = body2,
        button = button,
        caption = caption,
        overline = overline
    )

    fun materialTypography() = Typography(
        h1 = h1,
        h2 = h2,
        h3 = h3,
        h4 = h4,
        h5 = h5,
        h6 = h6,
        subtitle1 = subtitle1,
        subtitle2 = subtitle2,
        body1 = body1,
        body2 = body2,
        button = button,
        caption = caption,
        overline = overline
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Typography) return false

        if (h1 != other.h1) return false
        if (h2 != other.h2) return false
        if (h3 != other.h3) return false
        if (h4 != other.h4) return false
        if (h5 != other.h5) return false
        if (h6 != other.h6) return false
        if (subtitle1 != other.subtitle1) return false
        if (subtitle2 != other.subtitle2) return false
        if (body1 != other.body1) return false
        if (body2 != other.body2) return false
        if (button != other.button) return false
        if (caption != other.caption) return false
        if (overline != other.overline) return false

        return true
    }

    override fun hashCode(): Int {
        var result = h1.hashCode()
        result = 31 * result + h2.hashCode()
        result = 31 * result + h3.hashCode()
        result = 31 * result + h4.hashCode()
        result = 31 * result + h5.hashCode()
        result = 31 * result + h6.hashCode()
        result = 31 * result + subtitle1.hashCode()
        result = 31 * result + subtitle2.hashCode()
        result = 31 * result + body1.hashCode()
        result = 31 * result + body2.hashCode()
        result = 31 * result + button.hashCode()
        result = 31 * result + caption.hashCode()
        result = 31 * result + overline.hashCode()
        return result
    }

}

private fun TextStyle.withDefaultFontFamily(default: FontFamily): TextStyle {
    return if (fontFamily != null) this else copy(fontFamily = default)
}

val LocalAphidTypography = staticCompositionLocalOf<AphidTypography> {
    error("No AphidTypography provided")
}