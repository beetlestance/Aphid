package com.beetlestance.aphid

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beetlestance.base_android.AphidViewModel
import com.beetlestance.domain.executors.FetchRecipes
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import com.beetlestance.domain.invoke
import com.beetlestance.domain.observers.ObserveRecipes
import com.beetlestance.spoonacular_kotlin.SpoonacularImageHelper
import com.beetlestance.spoonacular_kotlin.constants.SpoonacularImageSize
import kotlinx.coroutines.flow.collect
import timber.log.Timber

class MainActivityViewModel @ViewModelInject constructor(
    fetchRecipes: FetchRecipes,
    observeRecipes: ObserveRecipes
) : AphidViewModel<ExploreViewState>(ExploreViewState()) {

    init {
        viewModelScope.launch {
            fetchRecipes().firstOrNull() ?: return@launch
        }

        observeRecipes()
        viewModelScope.launch {
            observeRecipes.observe().collectAndSetState { copy(breakfastRecipes = it) }
        }
    }
}