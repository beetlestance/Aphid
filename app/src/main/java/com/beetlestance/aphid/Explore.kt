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
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.isFocused
import androidx.compose.ui.focusObserver
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
            Search()
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


@OptIn(ExperimentalFocus::class)
@Composable
fun Search(state: SearchState = rememberSearchState()) {
    Surface {
        val placeHolder = "Palak Paneer"
        OutlinedTextField(
            value = state.query,
            onValueChange = {
                state.query = it
            },
            modifier = Modifier.fillMaxWidth().focusObserver {
                state.focused = it.isFocused
            },
            leadingIcon = {
                Icon(Icons.Outlined.Search)
            },
            label = { SearchHint(hint = state.hint) },
            placeholder = { SearchPlaceHolder(placeHolder = placeHolder) }
        )
    }
}

@Composable
fun SearchHint(hint: String) {
    Text(text = hint)
}

@Composable
fun SearchPlaceHolder(placeHolder: String) {
    Text(text = placeHolder)
}

@Composable
private fun rememberSearchState(
    query: String = "",
    focused: Boolean = false
): SearchState {
    return remember {
        SearchState(
            query = query,
            focused = focused
        )
    }
}

@Stable
class SearchState(
    query: String,
    focused: Boolean
) {
    var query by mutableStateOf(query)
    var focused by mutableStateOf(focused)

    val hint: String
        get() = if (focused) "Search For Food" else "Palak Paneer"
}
