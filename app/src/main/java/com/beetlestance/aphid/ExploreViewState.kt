package com.beetlestance.aphid

import androidx.compose.runtime.Immutable

@Immutable
data class ExploreViewState(
    val breakfastRecipes: List<Recipe> = emptyList()
)