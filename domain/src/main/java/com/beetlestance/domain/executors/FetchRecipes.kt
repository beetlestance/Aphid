package com.beetlestance.domain.executors

import com.beetlestance.base.utils.AppCoroutineDispatchers
import com.beetlestance.data.repositories.recipes.RecipesRepository
import com.beetlestance.domain.ResultUseCase
import com.beetlestance.domain.UseCase
import com.beetlestance.spoonacular_kotlin.models.response.recipe.RecipeInformation
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchRecipes @Inject constructor(
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
    private val recipesRepository: RecipesRepository
) : UseCase<Unit>() {
    override suspend fun doWork(params: Unit) {
        withContext(appCoroutineDispatchers.io) {
            recipesRepository.fetchRecipes()
        }
    }
}