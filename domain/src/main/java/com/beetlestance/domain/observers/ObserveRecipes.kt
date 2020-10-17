package com.beetlestance.domain.observers

import com.beetlestance.data.entities.Recipes
import com.beetlestance.data.repositories.recipes.RecipesRepository
import com.beetlestance.domain.ObserveUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveRecipes @Inject constructor(
    private val recipesRepository: RecipesRepository
) : ObserveUseCase<Unit, List<Recipes>>() {
    override fun createObservable(params: Unit): Flow<List<Recipes>> {
        return recipesRepository.observeRecipes()
    }
}