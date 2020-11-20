package com.beetlestance.aphid.feature_profile

import androidx.compose.runtime.Immutable
import com.beetlestance.aphid.data.entities.Recipe


@Immutable
data class ProfileViewState(
    val savedRecipes: List<Recipe> = emptyList(),
    val favouriteRecipes: List<Recipe> = emptyList()
)
