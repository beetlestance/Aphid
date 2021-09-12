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
package com.beetlestance.aphid.feature_explore

import android.graphics.drawable.Drawable
import androidx.annotation.FloatRange
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.graphics.blue
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.green
import androidx.core.graphics.red
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.beetlestance.aphid.base_android.R
import com.beetlestance.aphid.common_compose.DynamicThemePrimaryColorsFromImage
import com.beetlestance.aphid.common_compose.extensions.contrastAgainst
import com.beetlestance.aphid.common_compose.extensions.getBitmap
import com.beetlestance.aphid.common_compose.extensions.rememberMutableState
import com.beetlestance.aphid.common_compose.rememberDominantColorState

/**
 * This is the minimum amount of calculated contrast for a color to be used on top of the
 * surface color. These values are defined within the WCAG AA guidelines, and we use a value of
 * 3:1 which is the minimum for user-interface components.
 */
private const val MinContrastOfPrimaryVsSurface = 3f

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ExploreBreakfastCard(
    modifier: Modifier = Modifier,
    imageSrc: Any,
    isFavourite: Boolean,
    title: String,
    subTitle: String,
    description: String,
    onMarkFavourite: (Boolean) -> Unit = {}
) {
    val surfaceColor = MaterialTheme.colors.surface
    val dominantColorState = rememberDominantColorState(
        defaultColor = MaterialTheme.colors.onPrimary
    ) { color ->
        // We want a color which has sufficient contrast against the surface color
        color.contrastAgainst(surfaceColor) >= MinContrastOfPrimaryVsSurface
    }

    val context = LocalContext.current
    val drawable: MutableState<Drawable?> = rememberMutableState { null }
    LaunchedEffect(key1 = imageSrc) {
        drawable.value = getBitmap(context, imageSrc)
        if (drawable.value != null) {
            dominantColorState.updateColorsFromBitmap(
                (drawable.value ?: return@LaunchedEffect).toBitmap()
            )
        }
    }

    DynamicThemePrimaryColorsFromImage(
        dominantColorState = dominantColorState
    ) {
        Surface(
            modifier = modifier
                .aspectRatio(13 / 20f)
                .graphicsLayer(
                    shadowElevation = 32f,
                    shape = RoundedCornerShape(32.dp),
                    clip = true
                )
                .clip(RoundedCornerShape(32.dp)),
            color = dominantColorState.color
                .moveTo(
                    targetColor = Color.White,
                    percent = 0.8f,
                    blueOffset = 0.05f
                )
        ) {

            ConstraintLayout {
                val (image, favIcon, spacerDetail, detail) = createRefs()

                val endGuideLine = createGuidelineFromEnd(16.dp)
                val startGuideline = createGuidelineFromStart(16.dp)
                val topGuideline = createGuidelineFromTop(16.dp)
                val bottomGuideline = createGuidelineFromBottom(16.dp)

                Surface(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .constrainAs(image) {
                            linkTo(start = startGuideline, end = endGuideLine)
                            linkTo(top = topGuideline, bottom = bottomGuideline, bias = 0.1f)
                        }
                        .aspectRatio(1 / 1f),
                    shape = RoundedCornerShape(16.dp),
                    elevation = 16.dp
                ) {
                    Image(
                        painter = rememberImagePainter(data = drawable.value),
                        contentScale = ContentScale.Crop,
                        contentDescription = "BreakFastCard"
                    )
                }

                FavIcon(
                    modifier = Modifier
                        .constrainAs(favIcon) {
                            linkTo(start = startGuideline, end = endGuideLine, bias = 1f)
                            linkTo(top = topGuideline, bottom = bottomGuideline, bias = 0f)
                        },
                    isFavourite = isFavourite,
                    onMarkFavourite = onMarkFavourite
                )

                Spacer(
                    modifier = Modifier
                        .height(56.dp)
                        .constrainAs(spacerDetail) {
                            linkTo(top = image.bottom, bottom = bottomGuideline, bias = 0f)
                        }
                )

                BreakfastDescription(
                    modifier = Modifier
                        .constrainAs(detail) {
                            linkTo(start = startGuideline, end = endGuideLine, bias = 0f)
                            linkTo(top = spacerDetail.bottom, bottom = bottomGuideline, bias = 0f)
                            width = Dimension.fillToConstraints
                        },
                    title = title,
                    subTitle = subTitle,
                    description = description
                )
            }
        }
    }
}

@Composable
fun FavIcon(
    modifier: Modifier = Modifier,
    isFavourite: Boolean,
    onMarkFavourite: (Boolean) -> Unit
) {
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
        IconToggleButton(
            modifier = modifier
                .background(
                    shape = CircleShape,
                    color = colorResource(id = R.color.grey_400_alpha_30)
                ),
            content = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_like),
                    tint = animateColorAsState(
                        targetValue = colorResource(if (isFavourite) R.color.deep_orange_a200 else R.color.white)
                    ).value,
                    contentDescription = "Mark As Favourite Recipe"
                )
            },
            checked = isFavourite,
            onCheckedChange = onMarkFavourite
        )
    }
}

@Composable
fun BreakfastDescription(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    description: String
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.ExtraBold,
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = subTitle,
            maxLines = 1,
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.subtitle1
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = description,
            maxLines = 3,
            fontWeight = FontWeight.Light,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.body2
        )
    }
}

private fun Color.moveTo(
    targetColor: Color,
    @FloatRange(from = 0.0, to = 1.0) percent: Float,
    @FloatRange(from = 0.0, to = 1.0) redOffset: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) greenOffset: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) blueOffset: Float = 0f
): Color {
    val targetColorRgb = targetColor.toArgb()
    val dominantColorRgb = this.toArgb()
    val redDominant =
        (dominantColorRgb.red + (targetColorRgb.red - dominantColorRgb.red) * (percent + redOffset))
    val greenDominant =
        (dominantColorRgb.green + (targetColorRgb.green - dominantColorRgb.green) * (percent + greenOffset))
    val blueDominant =
        (dominantColorRgb.blue + (targetColorRgb.blue - dominantColorRgb.blue) * (percent + blueOffset))
    return Color(redDominant.toInt(), greenDominant.toInt(), blueDominant.toInt())
}
