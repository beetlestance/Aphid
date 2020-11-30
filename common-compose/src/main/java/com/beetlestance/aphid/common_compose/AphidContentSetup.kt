@file:Suppress("NOTHING_TO_INLINE")

package com.beetlestance.aphid.common_compose

import androidx.compose.runtime.Composable
import com.beetlestance.aphid.common_compose.insets.ProvideWindowInsets
import com.google.android.material.composethemeadapter.MdcTheme

@Composable
inline fun AphidContent(
    noinline content: @Composable () -> Unit
) {
    // Provides theme
    MdcTheme {
        // Provides window insets
        ProvideWindowInsets {
            content()
        }
    }
}