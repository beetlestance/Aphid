package com.beetlestance.aphid

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beetlestance.base_android.AphidViewModel
import com.beetlestance.domain.executors.FetchRecipes
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import com.beetlestance.domain.invoke
import com.beetlestance.spoonacular_kotlin.SpoonacularImageHelper
import com.beetlestance.spoonacular_kotlin.constants.SpoonacularImageSize
import timber.log.Timber

class MainActivityViewModel @ViewModelInject constructor(
    fetchRecipes: FetchRecipes
) : AphidViewModel<ExploreViewState>(ExploreViewState()) {

    init {
        viewModelScope.launch {
            val recipes = fetchRecipes().firstOrNull() ?: return@launch
            setState {
                this.copy(breakfastRecipes = recipes.map { recipeInformation ->
                    Recipe(
                        id = recipeInformation.id,
                        name = recipeInformation.title,
                        image = SpoonacularImageHelper.generateRecipeImageUrl(
                            id = recipeInformation.id?.toLong() ?: 0,
                            imageSize = SpoonacularImageSize.Recipe.ULTRA_HIGH_QUALITY,
                            imageType = recipeInformation.imageType ?: ""
                        )
                    )
                })
            }
        }
    }
}

data class Recipe(
    val id: Int?,
    val name: String? = null,
    val rating: Int? = null,
    val serving: Int? = null,
    val time: Long? = null,
    val calories: Long? = null,
    val image: String? = null
)