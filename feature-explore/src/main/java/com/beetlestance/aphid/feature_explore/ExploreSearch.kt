package com.beetlestance.aphid.feature_explore

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

private val ExposeSearchBarShape = CircleShape

@Preview
@Composable
fun ExposeSearch(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
) {
    var searchQuery by savedInstanceState(saver = TextFieldValue.Saver) {
        TextFieldValue("")
    }

    Box(
        modifier = modifier
            .padding(8.dp)
            .drawShadow(8.dp, ExposeSearchBarShape, false)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(ExposeSearchBarShape),
            value = searchQuery,
            placeholder = {
                Text(
                    text = "Search for food",
                    color = MaterialTheme.colors.onSurface
                )
            },
            leadingIcon = {
                Icon(
                    asset = Icons.Outlined.Search,
                    tint = MaterialTheme.colors.onSurface
                )
            },
            onValueChange = {
                onValueChange(it.text)
                searchQuery = it
            },
            backgroundColor = MaterialTheme.colors.surface,
            activeColor = MaterialTheme.colors.onSurface,
            inactiveColor = MaterialTheme.colors.surface
        )
    }
}