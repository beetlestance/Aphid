package com.beetlestance.aphid

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.foundation.layout.FlowMainAxisAlignment
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.SizeMode
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.EmphasisAmbient
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ConfigurationAmbient
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview


@Preview
@Composable
fun Explore() {
    Surface(color = MaterialTheme.colors.surface) {
        ScrollableColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.Top
            )
        ) {
            BreakFast()
        }
    }
}

@Composable
fun BreakFast() {

    Text(
        text = stringResource(id = R.string.explore_breaksfast_header),
        style = MaterialTheme.typography.h6,
    )

    ScrollableRow(
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.Start
        )
    ) {

        repeat(4) {
            BreakFastContent()
        }
    }
}

@Composable
fun BreakFastContent() {
    val itemWidth = (ConfigurationAmbient.current.screenWidthDp * 0.7f).dp
    Column(
        modifier = Modifier.preferredWidth(itemWidth).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(
            space = 4.dp,
            alignment = Alignment.Top
        )
    ) {

        BreakFastCard()

        BreakFastDetails()
    }
}

@Composable
fun BreakFastCard() {
    Card(shape = RoundedCornerShape(16.dp)) {
        Box {
            Image(
                modifier = Modifier.fillMaxWidth(),
                asset = imageResource(id = R.drawable.temp_brownie),
                contentScale = ContentScale.Crop
            )

            ProvideEmphasis(emphasis = EmphasisAmbient.current.medium) {
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
                    onCheckedChange = {}
                )
            }
        }
    }
}

@OptIn(ExperimentalLayout::class)
@Composable
fun BreakFastDetails() {
    FlowRow(
        mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
        mainAxisSize = SizeMode.Expand,
        crossAxisSpacing = 4.dp
    ) {
        Text(
            text = "2 Serving • 40 Min • 331 Cal ",
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
            text = "4.3",
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.surface
        )
    }

    Text(
        text = "Creamy Donut",
        style = MaterialTheme.typography.body1
    )
}
