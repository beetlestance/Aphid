@file:Suppress("NOTHING_TO_INLINE")

package com.beetlestance.aphid.common_compose

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.hilt.lifecycle.HiltViewModelFactory
import com.beetlestance.aphid.common_compose.utils.ProvideNavigationViewModelFactoryMap
import com.google.android.material.composethemeadapter.MdcTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@Composable
fun ComponentActivity.AphidContentSetUp(
    content: @Composable () -> Unit
) {
    setContent {
        // Provided mdc theme
        MdcTheme {
            // Provides window insets
            ProvideWindowInsets {
                // Provides view model
                ProvideNavigationViewModelFactoryMap(
                    factory = defaultViewModelProviderFactory as HiltViewModelFactory
                ) {
                    // actual content
                    content()
                }
            }
        }
    }
}