package com.beetlestance.aphid

import androidx.compose.foundation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Density
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

        BreakFastContent()

        BreakFastContent()
    }
}

@Composable
fun BreakFastContent() {
    Column(
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

@Composable
fun BreakFastDetails() {

    ConstraintLayout {
        val (button, text) = createRefs()

        Text(
            modifier = Modifier.constrainAs(button) {
                start.linkTo(anchor = parent.start)
                end.linkTo(anchor = text.start)
            },
            text = "2 Serving • 40 Min • 331 Cal ",
            style = MaterialTheme.typography.body2,
            color = colorResource(id = R.color.grey_700)
        )

        Text(
            modifier = Modifier
                .constrainAs(text) {
                    end.linkTo(anchor = parent.end)
                }.background(
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
