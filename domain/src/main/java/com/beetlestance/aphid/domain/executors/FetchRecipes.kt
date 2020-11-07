package com.beetlestance.aphid.domain.executors

import com.beetlestance.aphid.base.utils.AppCoroutineDispatchers
import com.beetlestance.aphid.data.repositories.recipes.RecipesRepository
import com.beetlestance.aphid.domain.UseCase
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
