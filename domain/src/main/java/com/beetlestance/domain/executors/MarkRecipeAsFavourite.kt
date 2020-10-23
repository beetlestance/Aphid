package com.beetlestance.domain.executors

import com.beetlestance.base.utils.AppCoroutineDispatchers
import com.beetlestance.data.entities.Recipe
import com.beetlestance.data.repositories.recipes.RecipesRepository
import com.beetlestance.domain.UseCase
import com.beetlestance.domain.executors.MarkRecipeAsFavourite.MarkRecipeAsFavouriteParams
import javax.inject.Inject

class MarkRecipeAsFavourite @Inject constructor(
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
    private val recipesRepository: RecipesRepository
) : UseCase<MarkRecipeAsFavouriteParams>() {

    override suspend fun doWork(params: MarkRecipeAsFavouriteParams) {
        recipesRepository.updateRecipe(params.recipe.copy(isFavourite = params.isFavourite))
    }

    data class MarkRecipeAsFavouriteParams(
        val recipe: Recipe,
        val isFavourite: Boolean
    )
}
