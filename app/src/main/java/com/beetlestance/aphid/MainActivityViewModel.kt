package com.beetlestance.aphid

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.beetlestance.base_android.AphidViewModel
import com.beetlestance.domain.executors.FetchRecipes
import kotlinx.coroutines.launch
import com.beetlestance.domain.invoke
import com.beetlestance.domain.observers.ObserveRecipes
import com.beetlestance.domain.watchStatus

class MainActivityViewModel @ViewModelInject constructor(
    fetchRecipes: FetchRecipes,
    observeRecipes: ObserveRecipes
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
            observeRecipes.observe().collectAndSetState { copy(breakfastRecipes = it) }
        }
    }
}