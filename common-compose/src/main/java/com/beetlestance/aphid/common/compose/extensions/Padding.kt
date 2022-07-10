package com.beetlestance.aphid.common.compose.extensions

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection

@Composable
operator fun PaddingValues.plus(other: PaddingValues): PaddingValues {
    return PaddingValues(
        start = calculateStartPadding(LocalLayoutDirection.current)
                + other.calculateStartPadding(LocalLayoutDirection.current),
        top = calculateTopPadding() + other.calculateTopPadding(),
        bottom = calculateBottomPadding() + other.calculateBottomPadding(),
        end = calculateEndPadding(LocalLayoutDirection.current) +
                other.calculateEndPadding(LocalLayoutDirection.current)
    )
}