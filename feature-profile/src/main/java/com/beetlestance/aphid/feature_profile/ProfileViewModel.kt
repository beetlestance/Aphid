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
package com.beetlestance.aphid.feature_profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.beetlestance.aphid.base_android.AphidViewModel
import com.beetlestance.aphid.domain.executors.MarkRecipeAsFavourite
import com.beetlestance.aphid.domain.observers.ObserveFavouriteRecipes
import com.beetlestance.aphid.domain.observers.ObserveSavedRecipes
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

internal class ProfileViewModel @ViewModelInject constructor(
    observeSavedRecipes: ObserveSavedRecipes,
    observeFavouriteRecipes: ObserveFavouriteRecipes,
    private val markRecipeAsFavourite: MarkRecipeAsFavourite
) : AphidViewModel<ProfileViewState>(ProfileViewState()) {

    private val pendingActions = Channel<ProfileActions>(Channel.BUFFERED)

    init {
        viewModelScope.launch {
            observeSavedRecipes.observe().collectAndSetState {
                copy(savedRecipes = it)
            }
        }

        observeSavedRecipes(Unit)

        viewModelScope.launch {
            observeFavouriteRecipes.observe().collectAndSetState {
                copy(favouriteRecipes = it)
            }
        }

        observeFavouriteRecipes(Unit)

        viewModelScope.launch {
            pendingActions.consumeAsFlow().collect { action ->
                when (action) {
                    is MarkFavourite -> markRecipeAsFavourite(action)
                }
            }
        }
    }

    internal fun submitAction(action: ProfileActions) {
        viewModelScope.launch {
            if (!pendingActions.isClosedForSend) {
                pendingActions.send(action)
            }
        }
    }

    private fun markRecipeAsFavourite(action: MarkFavourite) {
        viewModelScope.launch {
            markRecipeAsFavourite.executeSync(
                params = MarkRecipeAsFavourite.MarkRecipeAsFavouriteParams(
                    recipe = action.recipe,
                    isFavourite = action.isFavourite
                )
            )
        }
    }
}
