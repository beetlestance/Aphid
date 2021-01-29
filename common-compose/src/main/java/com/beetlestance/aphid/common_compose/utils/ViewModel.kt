/*
 * Copyright 2021 BeetleStance
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beetlestance.aphid.common_compose.utils

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableAmbient
import androidx.compose.runtime.Providers
import androidx.compose.runtime.staticAmbientOf
import androidx.compose.ui.platform.setContent
import androidx.hilt.lifecycle.ViewModelAssistedFactory
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.composable
import androidx.savedstate.SavedStateRegistryOwner
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory

/**
 * In essence using the activity or fragment as the [SavedStateRegistryOwner] when the
 * [ViewModelStoreOwner] is not the same will cause your ViewModel to be different between the your
 * two navigation destinations but because the [SavedStateRegistryOwner] has a higher scope it
 * complains when trying to provide the same SavedStateHandle that was already consumed.
 * We need to make [HiltViewModelFactory] use the [SavedStateRegistryOwner] provided by the Navigation
 * library and specifically the [NavBackStackEntry].
 *
 * @see <a href="https://github.com/google/dagger/issues/2166#issuecomment-723775543">Took from issue</a>
 * */

val AmbientApplication: ProvidableAmbient<Application> = staticAmbientOf()

val AmbientViewModelFactoriesMap: ProvidableAmbient<Map<String, ViewModelAssistedFactory<out ViewModel>>> =
    staticAmbientOf()

val AmbientNavBackStackEntry: ProvidableAmbient<NavBackStackEntry> = staticAmbientOf()

fun ComponentActivity.setUpWithViewModel(
    content: @Composable () -> Unit
) {
    setContent {
        ProvideNavigationViewModelFactoryMap(
            factory = defaultViewModelProviderFactory as HiltViewModelFactory,
            content = content
        )
    }
}

fun NavGraphBuilder.composableContent(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable () -> Unit
) {
    composable(route, arguments, deepLinks) {
        Providers(AmbientNavBackStackEntry provides it, content = content)
    }
}

@Composable
inline fun <reified VM : ViewModel> navViewModel(): ViewModelLazy<VM> {
    val backStackEntry = AmbientNavBackStackEntry.current

    val factory = if (backStackEntry.defaultViewModelProviderFactory is HiltViewModelFactory) {
        backStackEntry.defaultViewModelProviderFactory
    } else {
        val application = AmbientApplication.current
        val viewModelFactories = AmbientViewModelFactoriesMap.current
        val delegate = SavedStateViewModelFactory(application, backStackEntry, null)
        HiltViewModelFactory::class.java.declaredConstructors.first()
            .newInstance(backStackEntry, null, delegate, viewModelFactories) as HiltViewModelFactory
    }

    return ViewModelLazy(
        viewModelClass = VM::class,
        storeProducer = { backStackEntry.viewModelStore },
        factoryProducer = { factory }
    )
}

@Suppress("UNCHECKED_CAST")
@Composable
internal fun ComponentActivity.ProvideNavigationViewModelFactoryMap(
    factory: HiltViewModelFactory,
    content: @Composable () -> Unit
) {
    // Hack for navigation viewModel
    val factories = HiltViewModelFactory::class.java.getDeclaredField("mViewModelFactories")
        .also { it.isAccessible = true }
        .get(factory) as Map<String, ViewModelAssistedFactory<out ViewModel>>

    Providers(
        AmbientApplication provides application,
        AmbientViewModelFactoriesMap provides factories,
        content = content
    )
}
