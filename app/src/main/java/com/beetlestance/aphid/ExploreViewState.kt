package com.beetlestance.aphid

import com.beetlestance.spoonacular_kotlin.models.response.recipe.RecipeInformation

data class ExploreViewState(
    val breakfastRecipes: List<RecipeInformation> = emptyList(),
    val cousines: List<RecipeInformation> = emptyList(),
    val timelyRecipes: List<RecipeInformation> = emptyList()
)