package com.beetlestance.aphid.feature_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Profile(viewModel: ProfileViewModel) {
    val state by viewModel.liveData.observeAsState()
    val actions: (ProfileActions) -> Unit = { action -> viewModel.submitAction(action) }

    Profile(state ?: return, actions)
}

@Composable
internal fun Profile(
    state: ProfileViewState,
    actions: (ProfileActions) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize().background(Color.Magenta)) {

    }
}
