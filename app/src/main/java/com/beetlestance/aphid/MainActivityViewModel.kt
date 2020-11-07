package com.beetlestance.aphid

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.beetlestance.aphid.base_android.AphidViewModel
import com.beetlestance.aphid.data.entities.Recipe
import com.beetlestance.aphid.domain.executors.FetchRecipes
import com.beetlestance.aphid.domain.executors.MarkRecipeAsFavourite
import com.beetlestance.aphid.domain.invoke
import com.beetlestance.aphid.domain.observers.ObserveRecipes
import com.beetlestance.aphid.domain.watchStatus
import com.beetlestance.aphid.feature_explore.ExploreViewState
import kotlinx.coroutines.launch

class MainActivityViewModel @ViewModelInject constructor(
    fetchRecipes: FetchRecipes,
    observeRecipes: ObserveRecipes,
    private val markRecipeAsFavourite: MarkRecipeAsFavourite
) : AphidViewModel<ExploreViewState>(ExploreViewState()) {

    init {
        viewModelScope.launch {
            fetchRecipes().watchStatus(
                onStarted = {},
                onSuccess = {},
                onError = {}
            )
        }

        observeRecipes()
        viewModelScope.launch {
            observeRecipes.observe().collectAndSetState {
                copy(
                    breakfastRecipes = it,
                    markRecipeAsFavourite = ::markRecipeAsFavourite
                )
            }
        }
    }

    private fun markRecipeAsFavourite(recipe: Recipe, isFavourite: Boolean) {
        viewModelScope.launch {
            markRecipeAsFavourite.executeSync(
                params = MarkRecipeAsFavourite.MarkRecipeAsFavouriteParams(
                    recipe = recipe,
                    isFavourite = isFavourite
                )
            )
        }
    }
}
