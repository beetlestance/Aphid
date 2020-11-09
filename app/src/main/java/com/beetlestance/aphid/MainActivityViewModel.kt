package com.beetlestance.aphid

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.beetlestance.aphid.base_android.AphidViewModel
import com.beetlestance.aphid.data.entities.Recipe
import com.beetlestance.aphid.domain.executors.FetchRecipes
import com.beetlestance.aphid.domain.executors.MarkRecipeAsFavourite
import com.beetlestance.aphid.domain.invoke
import com.beetlestance.aphid.domain.observers.ObserveRecipes
import com.beetlestance.aphid.domain.onError
import com.beetlestance.aphid.domain.onStarted
import com.beetlestance.aphid.domain.onSuccess
import com.beetlestance.aphid.domain.watchStatus
import com.beetlestance.aphid.feature_explore.ExploreActions
import com.beetlestance.aphid.feature_explore.ExploreViewState
import com.beetlestance.aphid.feature_explore.MarkFavourite
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainActivityViewModel @ViewModelInject constructor(
    fetchRecipes: FetchRecipes,
    observeRecipes: ObserveRecipes,
    private val markRecipeAsFavourite: MarkRecipeAsFavourite
) : AphidViewModel<ExploreViewState>(ExploreViewState()) {

    private val pendingActions = Channel<ExploreActions>(Channel.BUFFERED)

    init {
        viewModelScope.launch {
            fetchRecipes().watchStatus {}
        }

        // have to set observe first
        viewModelScope.launch {
            observeRecipes.observe().collect {
                updateBreakfastRecipes(it)
            }
        }

        viewModelScope.launch {
            pendingActions.consumeAsFlow().collect { action ->
                when (action) {
                    is MarkFavourite -> markRecipeAsFavourite(action)
                }
            }
        }

        observeRecipes()
    }


    internal fun submitAction(action: ExploreActions) {
        viewModelScope.launch {
            if (!pendingActions.isClosedForSend) {
                pendingActions.send(action)
            }
        }
    }

    private fun updateBreakfastRecipes(recipes: List<Recipe>) {
        viewModelScope.launchSetState {
            copy(breakfastRecipes = recipes)
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
