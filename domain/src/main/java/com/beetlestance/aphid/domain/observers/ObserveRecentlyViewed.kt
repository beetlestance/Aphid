package com.beetlestance.aphid.domain.observers

import com.beetlestance.aphid.data.entities.Recipe
import com.beetlestance.aphid.data.repositories.recipes.RecipesRepository
import com.beetlestance.aphid.domain.ObserveUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveRecentlyViewed @Inject constructor(
    private val recipesRepository: RecipesRepository
) : ObserveUseCase<Int, List<Recipe>>() {

    override fun createObservable(params: Int): Flow<List<Recipe>> {
        return recipesRepository.observeRecentlyViewedRecipes(params)
    }
}
