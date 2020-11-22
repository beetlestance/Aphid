package com.beetlestance.aphid.common_compose

import androidx.compose.animation.animate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AmbientContentAlpha
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RecipeDetailedPosterCard(
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp,
    isFavourite: Boolean,
    onCheckedChange: (Boolean) -> Unit = {},
    cardShape: Shape = RoundedCornerShape(16.dp),
    posterImage: @Composable BoxScope.() -> Unit,
    posterDetails: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = 4.dp,
            alignment = Alignment.Top
        )
    ) {

        RecipePosterCard(
            modifier = Modifier.padding(bottom = 8.dp),
            elevation = elevation,
            posterImage = posterImage,
            cardShape = cardShape,
            isFavourite = isFavourite,
            onCheckChanged = onCheckedChange,
        )

        posterDetails()
    }
}

@Composable
fun RecipePosterCard(
    modifier: Modifier = Modifier,
    elevation: Dp,
    isFavourite: Boolean,
    cardShape: Shape = RoundedCornerShape(16.dp),
    onCheckChanged: (Boolean) -> Unit = {},
    posterImage: @Composable BoxScope.() -> Unit
) {
    Card(
        modifier = modifier,
        elevation = animate(elevation),
        shape = cardShape
    ) {
        Box(modifier = Modifier.clipToBounds()) {

            posterImage()

            Providers(AmbientContentAlpha provides ContentAlpha.high) {
                MarkFavouriteButton(
                    modifier = Modifier.align(Alignment.TopEnd),
                    isFavourite = isFavourite,
                    onCheckChanged = onCheckChanged
                )
            }
        }
    }
}

@Composable
fun MarkFavouriteButton(
    modifier: Modifier = Modifier,
    isFavourite: Boolean,
    onCheckChanged: (Boolean) -> Unit
) {
    IconToggleButton(
        modifier = modifier.padding(16.dp)
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
        onCheckedChange = onCheckChanged
    )
}
