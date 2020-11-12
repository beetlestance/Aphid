package com.beetlestance.aphid.feature_explore

import androidx.compose.runtime.Immutable
import com.beetlestance.aphid.data.entities.Recipe

@Immutable
data class ExploreViewState(
    val breakfastRecipes: List<Recipe> = emptyList(),
    val readyInTimeRecipes: List<Recipe> = emptyList(),
    val recentlyViewedRecipes: List<Recipe> = emptyList()
)
