package com.beetlestance.aphid.common_compose

import androidx.annotation.DrawableRes
import androidx.compose.animation.animate
import androidx.compose.foundation.background
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
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun FoodCardPage(
    modifier: Modifier = Modifier,
    pageConfig: PageConfig,
    isSelected: Boolean,
    imageUrl: String? = null,
    isFavourite: Boolean,
    @DrawableRes placeholder: Int,
    onCheckedChange: (Boolean) -> Unit = {},
    cardShape: Shape = RoundedCornerShape(16.dp),
    childPreferredHeight: Dp,
    children: @Composable ColumnScope.() -> Unit
) {
    val pageElevation = pageConfig.elevation(isSelected = isSelected)
    val pageWidth = pageConfig.width(isSelected = isSelected)
    val pageHeight = pageConfig.height(isSelected = isSelected) + childPreferredHeight

    FoodCard(
        elevation = if (pageConfig.enableDefaultAnimation) animate(pageElevation) else pageElevation,
        modifier = modifier
            .preferredWidth(if (pageConfig.enableDefaultAnimation) animate(pageWidth) else pageWidth)
            .preferredHeight(if (pageConfig.enableDefaultAnimation) animate(pageHeight) else pageHeight)
            .padding(horizontal = 16.dp),
        imageUrl = imageUrl,
        placeholder = placeholder,
        isFavourite = isFavourite,
        onCheckedChange = onCheckedChange,
        imageHeightFraction = (pageHeight - childPreferredHeight).value / pageHeight.value,
        cardShape = cardShape,
        children = children
    )
}

@Composable
fun FoodCard(
    elevation: Dp = 4.dp,
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    imageHeightFraction: Float = 1f,
    isFavourite: Boolean,
    @DrawableRes placeholder: Int,
    onCheckedChange: (Boolean) -> Unit = {},
    cardShape: Shape = RoundedCornerShape(16.dp),
    children: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = 4.dp,
            alignment = Alignment.Top
        )
    ) {

        FoodPosterCard(
            elevation = elevation,
            imageSrc = imageUrl ?: placeholder,
            cardShape = cardShape,
            isFavourite = isFavourite,
            onCheckChanged = onCheckedChange,
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(imageHeightFraction)
                .padding(bottom = 8.dp)
        )

        children()
    }
}

@Composable
fun FoodPosterCard(
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
        }
    }
}
