package com.beetlestance.aphid.common_compose

import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat

/**
 * Wrapper for [AndroidImage], to override the size to icon size
 */
@Composable
fun AndroidIcon(
    @DrawableRes drawableId: Int,
    tint: Color,
    modifier: Modifier = Modifier
) {
    AndroidImage(
        drawableId = drawableId,
        tint = tint,
        modifier = modifier.preferredSize(24.dp)
    )
}

/**
 * Creates a composable that will attempt to load drawable into Android ImageView.
 * Supports [AndroidVectorDrawable]
 *
 * Remove once coil can load animated vector drawable for Android 10
 */
@Composable
fun AndroidImage(
    @DrawableRes drawableId: Int,
    tint: Color,
    modifier: Modifier = Modifier
) {
    val animatedVectorDrawable = AnimatedVectorDrawableCompat.create(
        ContextAmbient.current,
        drawableId
    ) ?: return

    val androidImageView = AppCompatImageView(ContextAmbient.current)

    AndroidView(viewBlock = { androidImageView }, modifier = modifier) {
        with(it) {
            setColorFilter(tint.toArgb())
            setImageDrawable(animatedVectorDrawable)
        }

        runCatching { animatedVectorDrawable.start() }
    }
}