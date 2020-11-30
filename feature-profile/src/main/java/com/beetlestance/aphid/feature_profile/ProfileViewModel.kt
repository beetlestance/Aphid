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
