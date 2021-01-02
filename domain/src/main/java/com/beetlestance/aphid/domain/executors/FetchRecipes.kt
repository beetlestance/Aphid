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
