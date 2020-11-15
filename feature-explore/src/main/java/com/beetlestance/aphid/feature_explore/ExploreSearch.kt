package com.beetlestance.aphid.feature_explore

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

private val ExposeSearchBarShape = RoundedCornerShape(16.dp)

@Preview
@Composable
fun ExposeSearch(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {},
) {
    val searchQuery by savedInstanceState(saver = TextFieldValue.Saver) {
        TextFieldValue("")
    }
    Box(
        modifier = modifier
            .border(Dp.Hairline, Color.Black, ExposeSearchBarShape)
            .padding(8.dp)
    ) {
        TextField(
            value = searchQuery.text,
            onValueChange = onValueChange
        )
    }
}