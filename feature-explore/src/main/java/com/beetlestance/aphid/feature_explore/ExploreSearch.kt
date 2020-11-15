package com.beetlestance.aphid.feature_explore

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

private val ExposeSearchBarShape = CircleShape

@Preview
@Composable
fun ExposeSearchBar(
    modifier: Modifier = Modifier,
    text: String = "",
    searchClick: () -> Unit = {},
    filterClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .drawShadow(
                    elevation = 8.dp,
                    shape = ExposeSearchBarShape,
                    clip = false
                )
                .clip(ExposeSearchBarShape)
                .clickable(onClick = searchClick)
                .background(Color.White)
                .padding(8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                asset = Icons.Outlined.Search,
                tint = Color.Gray,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .align(Alignment.CenterVertically)
            )

            Text(
                text = if (text.isEmpty()) "Search for food" else text,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                color = Color.Gray
            )
        }

        Icon(
            asset = vectorResource(id = R.drawable.ic_filter),
            tint = Color.Gray,
            modifier = Modifier
                .clip(CircleShape)
                .clickable(
                    onClick = filterClick
                )
                .padding(8.dp)
                .align(Alignment.CenterVertically)
        )
    }
}