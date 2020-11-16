package com.beetlestance.aphid.feature_explore

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.animation.animate
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AmbientContentAlpha
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Providers
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.beetlestance.aphid.common_compose.DynamicThemePrimaryColorsFromImage
import com.beetlestance.aphid.common_compose.R
import com.beetlestance.aphid.common_compose.extensions.contrastAgainst
import com.beetlestance.aphid.common_compose.extensions.getBitmap
import com.beetlestance.aphid.common_compose.extensions.rememberMutableState
import com.beetlestance.aphid.common_compose.extensions.toColor
import com.beetlestance.aphid.common_compose.extensions.verticalGradientBackground
import com.beetlestance.aphid.common_compose.rememberDominantColorState
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.imageloading.toPainter


/**
 * This is the minimum amount of calculated contrast for a color to be used on top of the
 * surface color. These values are defined within the WCAG AA guidelines, and we use a value of
 * 3:1 which is the minimum for user-interface components.
 */
private const val MinContrastOfPrimaryVsSurface = 3f

@Composable
fun ExploreBreakfastCard(
    modifier: Modifier = Modifier,
    imageSrc: Any,
    isFavourite: Boolean,
    title: String,
    subTitle: String,
    description: String,
    elevation: Dp = 8.dp,
    onMarkFavourite: (Boolean) -> Unit = {},
    onOpen: () -> Unit = {}
) {
    val surfaceColor = MaterialTheme.colors.surface
    val dominantColorState = rememberDominantColorState(
        defaultColor = MaterialTheme.colors.onPrimary
    ) { color ->
        // We want a color which has sufficient contrast against the surface color
        color.contrastAgainst(surfaceColor) >= MinContrastOfPrimaryVsSurface
    }
    DynamicThemePrimaryColorsFromImage(
        dominantColorState = dominantColorState
    ) {
        Color.LightGray
        val dominantColors = listOf(dominantColorState.color, "#fafafa".toColor())
        Surface(
            modifier = modifier
                .padding(32.dp)
                .aspectRatio(13 / 20f)
                .drawLayer(
                    shadowElevation = 16f,
                    shape = RoundedCornerShape(32.dp),
                    clip = true
                )
                .verticalGradientBackground(dominantColors)
                .clip(RoundedCornerShape(32.dp)),
            color = Color.Transparent
        ) {

            ConstraintLayout {
                val (image, favIcon, spacerDetail, detail) = createRefs()

                val endGuideLine = createGuidelineFromEnd(16.dp)
                val startGuideline = createGuidelineFromStart(16.dp)
                val topGuideline = createGuidelineFromTop(16.dp)
                val bottomGuideline = createGuidelineFromBottom(16.dp)

                val context = ContextAmbient.current
                val drawable: MutableState<Drawable?> = rememberMutableState { null }
                LaunchedEffect(subject = imageSrc) {
                    drawable.value = getBitmap(context, imageSrc)
                    if (drawable.value != null) {
                        dominantColorState.updateColorsFromBitmap(drawable.value!!.toBitmap())
                    }
                }

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
                    if(drawable.value != null){
                        Image(
                            painter = drawable.value!!.toPainter(),
                            contentScale = ContentScale.Crop
                        )
                    }
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
                        .preferredHeight(56.dp)
                        .constrainAs(spacerDetail) {
                            linkTo(top = image.bottom, bottom = bottomGuideline, bias = 0f)
                        }
                )

                BreakfastDescription(
                    modifier = Modifier
                        .constrainAs(detail) {
                            linkTo(start = startGuideline, end = endGuideLine, bias = 0f)
                            linkTo(top = spacerDetail.bottom, bottom = bottomGuideline, bias = 0f)
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
    Providers(AmbientContentAlpha provides ContentAlpha.high) {
        IconToggleButton(
            modifier = modifier
                .background(
                    shape = CircleShape,
                    color = colorResource(id = R.color.grey_400_alpha_30)
                ),
            icon = {
                Icon(
                    asset = vectorResource(id = R.drawable.ic_like),
                    tint = animate(target = colorResource(if (isFavourite) R.color.deep_orange_a200 else R.color.white))
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

        Spacer(modifier = Modifier.preferredHeight(8.dp))

        Text(
            text = subTitle,
            maxLines = 1,
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.subtitle1
        )

        Spacer(modifier = Modifier.preferredHeight(24.dp))

        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = description,
            maxLines = 3,
            fontWeight = FontWeight.Light,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.body2
        )
    }
}


