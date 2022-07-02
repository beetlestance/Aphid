/*
 * Copyright 2021 BeetleStance
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beetlestance.aphid.common.compose

import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
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
        modifier = modifier.size(24.dp)
    )
}

/**
 * Creates a composable that will attempt to load drawable into Android ImageView.
 * Supports [AnimatedVectorDrawableCompat]
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
        LocalContext.current,
        drawableId
    ) ?: return

    val androidImageView = AppCompatImageView(LocalContext.current)

    AndroidView(factory = { androidImageView }, modifier = modifier) {
        with(it) {
            setColorFilter(tint.toArgb())
            setImageDrawable(animatedVectorDrawable)
        }

        runCatching { animatedVectorDrawable.start() }
    }
}
