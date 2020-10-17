package com.beetlestance.aphid

import androidx.compose.runtime.Immutable
import com.beetlestance.data.entities.Recipes

@Immutable
data class ExploreViewState(
    val breakfastRecipes: List<Recipes> = emptyList()
)