package com.beetlestance.aphid

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beetlestance.aphid.base_android.AphidViewModel
import com.beetlestance.aphid.data.entities.Recipe
import com.beetlestance.aphid.domain.executors.FetchRecipes
import com.beetlestance.aphid.domain.executors.MarkRecipeAsFavourite
import com.beetlestance.aphid.domain.invoke
import com.beetlestance.aphid.domain.observers.ObserveRecipes
import com.beetlestance.aphid.domain.observers.ObserveRecipesReadyInTime
import com.beetlestance.aphid.domain.onError
import com.beetlestance.aphid.domain.onStarted
import com.beetlestance.aphid.domain.onSuccess
import com.beetlestance.aphid.domain.watchStatus
import com.beetlestance.aphid.feature_explore.ExploreActions
import com.beetlestance.aphid.feature_explore.ExploreViewState
import com.beetlestance.aphid.feature_explore.MarkFavourite
import com.beetlestance.spoonacular_kotlin.constants.MealType
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainActivityViewModel @ViewModelInject constructor(
    fetchRecipes: FetchRecipes,
    observeRecipes: ObserveRecipes,
    observeRecipesReadyInTime: ObserveRecipesReadyInTime,
    private val markRecipeAsFavourite: MarkRecipeAsFavourite
) : AphidViewModel<ExploreViewState>(ExploreViewState()) {

    private val pendingActions = Channel<ExploreActions>(Channel.BUFFERED)

    init {
        viewModelScope.launch {
            val param = FetchRecipes.FetchRecipesParams(
                type = MealType.BREAKFAST,
                numberOfRecipes = 10
            )
            fetchRecipes(param).watchStatus {}
        }

        viewModelScope.launch {
            val param = FetchRecipes.FetchRecipesParams(
                maxReadyTime = 30,
                numberOfRecipes = 5
            )
            fetchRecipes(param).watchStatus {}
        }

        viewModelScope.launch {
            observeRecipes.observe().collectAndSetState {
                copy(breakfastRecipes = it)
            }
        }

        observeRecipes()

        viewModelScope.launch {
            observeRecipesReadyInTime.observe().collectAndSetState {
                copy(readyInTimeRecipes = it)
            }
        }

        observeRecipesReadyInTime(30)

        viewModelScope.launch {
            pendingActions.consumeAsFlow().collect { action ->
                when (action) {
                    is MarkFavourite -> markRecipeAsFavourite(action)
                }
            }
        }
    }


    internal fun submitAction(action: ExploreActions) {
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
