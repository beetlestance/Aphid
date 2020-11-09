package com.beetlestance.aphid.common_compose

import androidx.annotation.DrawableRes
import androidx.compose.animation.animate
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AmbientEmphasisLevels
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.ProvideEmphasis
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.beetlestance.aphid.common_compose.pager.PageConfig
import com.beetlestance.aphid.common_compose.utils.widthPercentage
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun FoodCardWithDetailsPage(
    modifier: Modifier = Modifier,
    pageConfig: PageConfig,
    isSelected: Boolean,
    imageUrl: String? = null,
    isFavourite: Boolean,
    @DrawableRes imageResource: Int,
    onCheckedChange: (Boolean) -> Unit = {},
    cardShape: Shape = RoundedCornerShape(16.dp),
    children: @Composable ColumnScope.() -> Unit
) {
    FoodCardWithDetails(
        elevation = pageConfig.elevation(isSelected = isSelected),
        modifier = modifier
            .preferredWidth(pageConfig.width(isSelected = isSelected))
            .preferredHeight(pageConfig.height(isSelected = isSelected))
            .padding(horizontal = 16.dp),
        fraction = null,
        horizontalOffset = pageConfig.horizontalOffset,
        imageUrl = imageUrl,
        imageResource = imageResource,
        isFavourite = isFavourite,
        onCheckedChange = onCheckedChange,
        cardShape = cardShape,
        children = children
    )
}

@Composable
fun FoodCardWithDetails(
    elevation: Dp = 4.dp,
    modifier: Modifier = Modifier,
    fraction: Float?,
    horizontalOffset: Dp,
    imageUrl: String? = null,
    isFavourite: Boolean,
    @DrawableRes imageResource: Int,
    onCheckedChange: (Boolean) -> Unit = {},
    cardShape: Shape = RoundedCornerShape(16.dp),
    children: @Composable ColumnScope.() -> Unit
) {
    // per item width in row
    if (fraction != null) {
        val itemWidth: Dp = widthPercentage(
            fraction = fraction,
            excludeRootPadding = horizontalOffset
        )
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
            isFavourite = isFavourite,
            onCheckChanged = onCheckedChange,
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.9f).padding(bottom = 8.dp)
        )

        children()
    }
}

@Composable
fun FoodCardWithFavIcon(
    elevation: Dp,
    imageSrc: Any,
    isFavourite: Boolean,
    cardShape: Shape = RoundedCornerShape(16.dp),
    onCheckChanged: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = animate(elevation),
        shape = cardShape
    ) {
        Box {
            CoilImage(
                data = imageSrc,
                fadeIn = true,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize().clipToBounds()
            )

            ProvideEmphasis(emphasis = AmbientEmphasisLevels.current.medium) {
                IconToggleButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp)
                        .clickable {

                        }
                        .background(
                            shape = CircleShape,
                            color = colorResource(id = R.color.grey_400_alpha_30)
                        ),
                    icon = {
                        Icon(
                            asset = vectorResource(id = R.drawable.ic_like),
                            tint = colorResource(if (isFavourite) R.color.deep_orange_a200 else R.color.white)
                        )
                    },
                    checked = isFavourite,
                    onCheckedChange = onCheckChanged
                )
            }
        }
    }
}
