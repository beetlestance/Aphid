package com.beetlestance.aphid.domain.executors

import com.beetlestance.aphid.base.utils.AppCoroutineDispatchers
import com.beetlestance.aphid.data.repositories.recipes.RecipesRepository
import com.beetlestance.aphid.domain.UseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchRecipes @Inject constructor(
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
    private val recipesRepository: RecipesRepository
) : UseCase<FetchRecipes.FetchRecipesParams>() {

    override suspend fun doWork(params: FetchRecipesParams) {
        withContext(appCoroutineDispatchers.io) {
            recipesRepository.fetchRecipes(
                maxReadyTime = params.maxReadyTime,
                type = params.type,
                numberOfRecipes = params.numberOfRecipes
            )
        }
    }

    data class FetchRecipesParams(
        val maxReadyTime: Long? = null,
        val type: String? = null,
        val numberOfRecipes: Int = 1
    )
}
