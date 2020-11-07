package com.beetlestance.aphid.feature_explore

import com.beetlestance.aphid.data.entities.Recipe

sealed class ExploreActions

data class MarkFavourite(
    val recipe: Recipe,
    val isFavourite: Boolean
) : ExploreActions()