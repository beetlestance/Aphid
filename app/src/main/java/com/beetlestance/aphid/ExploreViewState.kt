package com.beetlestance.aphid

import androidx.compose.runtime.Immutable
import com.beetlestance.data.entities.Recipe

@Immutable
data class ExploreViewState(
    val breakfastRecipes: List<Recipe> = emptyList(),
    val markRecipeAsFavourite: (Recipe, Boolean) -> Unit = { _, _ -> }
)
