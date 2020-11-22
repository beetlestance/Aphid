package com.beetlestance.aphid.feature_profile

import com.beetlestance.aphid.data.entities.Recipe

sealed class ProfileActions

data class MarkFavourite(
    val recipe: Recipe,
    val isFavourite: Boolean
) : ProfileActions()
