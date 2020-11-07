package com.beetlestance.aphid.domain.observers

import com.beetlestance.aphid.data.entities.Recipe
import com.beetlestance.aphid.data.repositories.recipes.RecipesRepository
import com.beetlestance.aphid.domain.ObserveUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveRecipes @Inject constructor(
    private val recipesRepository: RecipesRepository
) : ObserveUseCase<Unit, List<Recipe>>() {

    override fun createObservable(params: Unit): Flow<List<Recipe>> {
        return recipesRepository.observeRecipes()
    }
}
