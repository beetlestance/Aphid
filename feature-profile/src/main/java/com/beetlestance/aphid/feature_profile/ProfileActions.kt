package com.beetlestance.aphid.feature_profile

import com.beetlestance.aphid.data.entities.Recipe

internal sealed class ProfileActions

internal data class MarkFavourite(
    val recipe: Recipe,
    val isFavourite: Boolean
) : ProfileActions()
