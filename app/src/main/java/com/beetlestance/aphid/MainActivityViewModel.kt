package com.beetlestance.aphid

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beetlestance.base_android.AphidViewModel
import com.beetlestance.domain.executors.FetchRecipes
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import com.beetlestance.domain.invoke
import timber.log.Timber

class MainActivityViewModel @ViewModelInject constructor(
    fetchRecipes: FetchRecipes
) : AphidViewModel<ExploreViewState>(ExploreViewState()) {

    init {
        viewModelScope.launch {
            val recipes = fetchRecipes().firstOrNull() ?: return@launch
            setState {
                this.copy(breakfastRecipes = recipes)
            }
        }
    }
}