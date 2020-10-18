package com.beetlestance.aphid

import androidx.annotation.DrawableRes
import androidx.compose.animation.animate
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.foundation.layout.FlowCrossAxisAlignment
import androidx.compose.foundation.layout.FlowMainAxisAlignment
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.SizeMode
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AmbientEmphasisLevels
import androidx.compose.material.Card
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ConfigurationAmbient
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun FoodCardWithDetailsPage(
    modifier: Modifier = Modifier,
    fraction: Float,
    horizontalOffset: Dp,
    horizontalOffsetFraction: Float,
    aspectRatio: Float = 4 / 5f,
    rating: String,
    name: String,
    imageUrl: String? = null,
    contentTags: String,
    @DrawableRes imageResource: Int,
    onCheckedChange: (Boolean) -> Unit = {},
    cardShape: Shape = RoundedCornerShape(16.dp),
    isSelected: Boolean
) {
    val itemWidth: Dp =
        (ConfigurationAmbient.current.screenWidthDp * fraction).dp - horizontalOffset.times(2)

    val width = if (isSelected) itemWidth else itemWidth - itemWidth.times(horizontalOffsetFraction)
    val height = itemWidth / aspectRatio
    val animateElevation = if (isSelected) 12.dp else 2.dp

    FoodCardWithDetails(
        elevation = animateElevation,
        modifier = modifier
            .preferredWidth(animate(width))
            .preferredHeight(animate(height))
            .padding(16.dp),
        fraction = null,
        horizontalOffset = horizontalOffset,
        rating = rating,
        name = name,
        imageUrl = imageUrl,
        contentTags = contentTags,
        imageResource = imageResource,
        onCheckedChange = onCheckedChange,
        cardShape = cardShape
    )
}

@Composable
fun FoodCardWithDetails(
    elevation: Dp = 4.dp,
    modifier: Modifier = Modifier,
    fraction: Float?,
    horizontalOffset: Dp,
    rating: String,
    name: String,
    imageUrl: String? = null,
    contentTags: String,
    @DrawableRes imageResource: Int,
    onCheckedChange: (Boolean) -> Unit = {},
    cardShape: Shape = RoundedCornerShape(16.dp)
) {
    // per item width in row
    if (fraction != null) {
        val itemWidth: Dp =
            (ConfigurationAmbient.current.screenWidthDp * fraction).dp - horizontalOffset.times(2)
        modifier.preferredWidth(itemWidth).fillMaxWidth()
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = 4.dp,
            alignment = Alignment.Top
        )
    ) {

        FoodCardWithFavIcon(
            elevation = elevation,
            imageSrc = imageUrl ?: imageResource,
            cardShape = cardShape,
            onCheckChanged = onCheckedChange,
            modifier = Modifier.fillMaxWidth()
        )

        FoodCardContentsDetails(
            contentTags = contentTags,
            rating = rating,
            name = name
        )
    }
}

@Composable
fun FoodCardWithFavIcon(
    elevation: Dp,
    imageSrc: Any,
    cardShape: Shape = RoundedCornerShape(16.dp),
    onCheckChanged: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        elevation = animate(elevation),
        shape = cardShape
    ) {
        Box {
            CoilImage(
                data = imageSrc,
                fadeIn = true,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .aspectRatio(3 / 4f)
                    .clipToBounds()
            )

            ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
                IconToggleButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                        .background(
                            shape = CircleShape,
                            color = colorResource(id = R.color.grey_400_alpha_30)
                        ),
                    icon = {
                        Icon(
                            asset = vectorResource(id = R.drawable.ic_like),
                            tint = colorResource(R.color.deep_orange_a200)
                        )
                    },
                    checked = false,
                    onCheckedChange = onCheckChanged
                )
            }
        }
    }
}

@OptIn(ExperimentalLayout::class)
@Composable
fun FoodCardContentsDetails(
    contentTags: String,
    rating: String,
    name: String
) {
    FlowRow(
        mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
        mainAxisSize = SizeMode.Expand,
        crossAxisAlignment = FlowCrossAxisAlignment.Center,
        crossAxisSpacing = 4.dp
    ) {
        Text(
            text = contentTags,
            style = MaterialTheme.typography.body2,
            color = colorResource(id = R.color.grey_700)
        )

        Text(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.deep_orange_a200),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 12.dp, vertical = 2.dp),
            text = rating,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.surface
        )
    }

    Text(
        text = name,
        style = MaterialTheme.typography.body1
    )
}