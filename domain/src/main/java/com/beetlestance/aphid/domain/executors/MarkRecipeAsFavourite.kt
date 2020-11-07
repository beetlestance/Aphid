package com.beetlestance.aphid.domain.executors

import com.beetlestance.aphid.base.utils.AppCoroutineDispatchers
import com.beetlestance.aphid.data.entities.Recipe
import com.beetlestance.aphid.data.repositories.recipes.RecipesRepository
import com.beetlestance.aphid.domain.UseCase
import com.beetlestance.aphid.domain.executors.MarkRecipeAsFavourite.MarkRecipeAsFavouriteParams
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MarkRecipeAsFavourite @Inject constructor(
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
    private val recipesRepository: RecipesRepository
) : UseCase<MarkRecipeAsFavouriteParams>() {

    override suspend fun doWork(params: MarkRecipeAsFavouriteParams) {
        withContext(appCoroutineDispatchers.io) {
            recipesRepository.updateRecipe(params.recipe.copy(isFavourite = params.isFavourite))
        }
    }

    data class MarkRecipeAsFavouriteParams(
        val recipe: Recipe,
        val isFavourite: Boolean
    )
}
