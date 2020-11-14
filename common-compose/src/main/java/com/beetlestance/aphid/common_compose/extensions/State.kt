package com.beetlestance.aphid.common_compose.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
inline fun <T> rememberMutableState(init: () -> T) = remember { mutableStateOf(init()) }

@Composable
inline fun <T> rememberMutableStateFor(key: Any, init: () -> T) =
    remember(key) { mutableStateOf(init()) }