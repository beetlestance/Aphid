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

import android.content.Context
import android.graphics.Bitmap
import androidx.collection.LruCache
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.Coil
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Scale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun DynamicThemePrimaryColorsFromImage(
    dominantColorState: DominantColorState = rememberDominantColorState(),
    content: @Composable () -> Unit
) {
    val colors = MaterialTheme.colors.copy(
        primary = dominantColorState.color,
        onPrimary = dominantColorState.onColor.copy(0.7f),
    )
    MaterialTheme(colors = colors, content = content)
}

@Composable
fun rememberDominantColorState(
    context: Context = LocalContext.current,
    defaultColor: Color = MaterialTheme.colors.primary,
    defaultOnColor: Color = MaterialTheme.colors.onPrimary,
    cacheSize: Int = 12,
    isColorValid: (Color) -> Boolean = { true }
): DominantColorState = remember {
    DominantColorState(context, defaultColor, defaultOnColor, cacheSize, isColorValid)
}

@Stable
class DominantColorState(
    private val context: Context,
    private val defaultColor: Color,
    private val defaultOnColor: Color,
    cacheSize: Int = 12,
    private val isColorValid: (Color) -> Boolean = { true }
) {
    var color: Color by mutableStateOf(defaultColor)
        private set
    var onColor: Color by mutableStateOf(defaultOnColor)
        private set

    private val cache = when {
        cacheSize > 0 -> LruCache<Any, DominantColors>(cacheSize)
        else -> null
    }

    suspend fun updateColorsFromImageUrl(url: Any) {
        val result = calculateDominantColor(url)
        color = result?.color ?: defaultColor
        onColor = result?.onColor ?: defaultOnColor
    }

    fun updateColorsFromBitmap(bitmap: Bitmap) {
        val dominantColors = calculateDominantColor(bitmap)
        color = dominantColors?.color ?: defaultColor
        onColor = dominantColors?.onColor ?: defaultOnColor
    }

    private fun calculateDominantColor(bitmap: Bitmap): DominantColors? {
        val hash = bitmap.hashCode()
        val cached = cache?.get(hash)

        if (cached != null) return cached

        return bitmap.dominantColorOrNull()
            .let { palette ->
                palette.vibrantSwatch?.takeIf { swatch -> isColorValid(Color(swatch.rgb)) }
                    ?: palette.swatches.filter { swatch -> isColorValid(Color(swatch.rgb)) }
                        .maxByOrNull { swatch -> swatch.population }
            }?.let { swatch ->
                DominantColors(
                    color = Color(swatch.rgb),
                    onColor = Color(swatch.bodyTextColor)
                )
            }?.also { result -> cache?.put(hash, result) }
    }

    private suspend fun calculateDominantColor(url: Any): DominantColors? {
        val cached = cache?.get(url)
        if (cached != null) {
            // If we already have the result cached, return early now...
            return cached
        }

        // Otherwise we calculate the swatches in the image, and return the first valid color
        return calculateSwatchesInImage(context, url)
            ?.let { palette ->
                palette.vibrantSwatch?.takeIf { swatch -> isColorValid(Color(swatch.rgb)) }
                    ?: palette.swatches
                        .filter { swatch -> isColorValid(Color(swatch.rgb)) }
                        .maxByOrNull { swatch -> swatch.population }

            }
            // If we found a valid swatch, wrap it in a [DominantColors]
            ?.let { swatch ->
                DominantColors(
                    color = Color(swatch.rgb),
                    onColor = Color(swatch.bodyTextColor).copy(alpha = 1f)
                )
            }
            // Cache the resulting [DominantColors]
            ?.also { result -> cache?.put(url, result) }
    }

    /**
     * Reset the color values to [defaultColor].
     */
    fun reset() {
        color = defaultColor
        onColor = defaultColor
    }
}

@Immutable
private data class DominantColors(
    val color: Color,
    val onColor: Color
)

/**
 * Fetches the given [imageUrl] with [Coil], then uses [Palette] to calculate the dominant color.
 */
private suspend fun calculateSwatchesInImage(
    context: Context,
    imageUrl: Any
): Palette? {
    val r = ImageRequest.Builder(context)
        .data(imageUrl)
        // We scale the image to cover 128px x 128px (i.e. min dimension == 128px)
        .size(128).scale(Scale.FILL)
        // Disable hardware bitmaps, since Palette uses Bitmap.getPixels()
        .allowHardware(false)
        .build()

    val bitmap: Bitmap? = when (val result = context.imageLoader.execute(r)) {
        is SuccessResult -> result.drawable.toBitmap()
        else -> null
    }

    return bitmap?.let {
        withContext(Dispatchers.Default) {
            return@withContext Palette.Builder(bitmap)
                // We reduce the maximum color count down to 32
                .maximumColorCount(8)
                // Disable any bitmap resizing in Palette. We've already loaded an appropriately
                // sized bitmap through Coil
                .resizeBitmapArea(0)
                // Clear any built-in filters. We want the unfiltered dominant color
                .clearFilters()
                .generate()
        }
    }
}

internal fun Bitmap.dominantColorOrNull(): Palette {
    return Palette.Builder(this)
        .maximumColorCount(8)
        .resizeBitmapArea(0)
        .clearFilters()
        .generate()
}
