/*
 * Copyright 2021 BeetleStance
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beetlestance.aphid.feature.explore

import androidx.lifecycle.viewModelScope
import com.beetlestance.aphid.base.android.ReduxStateViewModel
import com.beetlestance.aphid.data.entities.Recipe
import com.beetlestance.aphid.domain.executors.FetchRecipes
import com.beetlestance.aphid.domain.executors.MarkRecipeAsFavourite
import com.beetlestance.aphid.domain.invoke
import com.beetlestance.aphid.domain.observers.ObserveRecentlyViewed
import com.beetlestance.aphid.domain.observers.ObserveRecipes
import com.beetlestance.aphid.domain.observers.ObserveRecipesWithReadyTime
import com.beetlestance.aphid.domain.watchStatus
import com.beetlestance.aphid.spoonacular.kotlin.constants.MealType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ExploreViewModel @Inject constructor(
    fetchRecipes: FetchRecipes,
    observeRecipes: ObserveRecipes,
    observeQuickRecipes: ObserveRecipesWithReadyTime,
    observeRecentlyViewed: ObserveRecentlyViewed,
    private val markRecipeAsFavourite: MarkRecipeAsFavourite
) : ReduxStateViewModel<ExploreViewState>(ExploreViewState()) {

    init {
        viewModelScope.launch {
            val param = FetchRecipes.FetchRecipesParams(
                type = MealType.BREAKFAST,
                numberOfRecipes = 10
            )
            fetchRecipes(param).watchStatus {}
        }

        viewModelScope.launch {
            val param = FetchRecipes.FetchRecipesParams(
                maxReadyTime = 30,
                numberOfRecipes = 5
            )
            fetchRecipes(param).watchStatus {}
        }

        viewModelScope.launch {
            observeRecipes.observe().collectAndSetState {
                copy(breakfastRecipes = it)
            }
        }

        observeRecipes()

        viewModelScope.launch {
            observeQuickRecipes.observe().collectAndSetState {
                copy(readyInTimeRecipes = it)
            }
        }

        observeQuickRecipes(30)

        viewModelScope.launch {
            observeRecentlyViewed.observe().collectAndSetState {
                copy(recentlyViewedRecipes = it)
            }
        }

        observeRecentlyViewed(5)
    }

    fun markRecipeAsFavourite(recipe: Recipe, isFavourite: Boolean) {
        viewModelScope.launch {
            markRecipeAsFavourite.executeSync(
                params = MarkRecipeAsFavourite.MarkRecipeAsFavouriteParams(
                    recipe = recipe,
                    isFavourite = isFavourite
                )
            )
        }
    }
}
