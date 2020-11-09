package com.beetlestance.aphid.domain.observers

import com.beetlestance.aphid.data.entities.Recipe
import com.beetlestance.aphid.data.repositories.recipes.RecipesRepository
import com.beetlestance.aphid.domain.ObserveUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveRecipesReadyInTime @Inject constructor(
    private val recipesRepository: RecipesRepository
) : ObserveUseCase<Long, List<Recipe>>() {

    override fun createObservable(params: Long): Flow<List<Recipe>> {
        return recipesRepository.observeRecipesReadyIn(params)
    }
}
