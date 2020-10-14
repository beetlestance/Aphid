package com.beetlestance.aphid

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beetlestance.domain.executors.FetchRecipes
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import com.beetlestance.domain.invoke
import timber.log.Timber

class MainActivityViewModel @ViewModelInject constructor(
    fetchRecipes: FetchRecipes
) : ViewModel() {

    init {
        viewModelScope.launch {
            val recipes = fetchRecipes().firstOrNull()
            Timber.d("Recipes fetched $recipes")
        }
    }
}