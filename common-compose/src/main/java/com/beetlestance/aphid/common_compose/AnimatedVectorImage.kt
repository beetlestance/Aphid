package com.beetlestance.aphid.common_compose

import android.graphics.drawable.AnimatedVectorDrawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat

@Composable
fun AndroidAVDIcon(
    @DrawableRes drawableId: Int,
    tint: Color,
    modifier: Modifier = Modifier
) {
    AndroidAVDImage(
        drawableId = drawableId,
        tint = tint,
        modifier = modifier.preferredSize(24.dp)
    )
}

@Composable
fun AndroidAVDImage(
    @DrawableRes drawableId: Int,
    tint: Color,
    modifier: Modifier = Modifier
) {
    val animatedVectorDrawable = AnimatedVectorDrawableCompat.create(
        ContextAmbient.current,
        drawableId
    ) ?: return

    val androidImageView = ImageView(ContextAmbient.current).apply {
        setColorFilter(tint.toArgb())
        setImageDrawable(animatedVectorDrawable)
    }

    AndroidView(viewBlock = { androidImageView }, modifier = modifier) {
        runCatching { animatedVectorDrawable.start() }
    }
}