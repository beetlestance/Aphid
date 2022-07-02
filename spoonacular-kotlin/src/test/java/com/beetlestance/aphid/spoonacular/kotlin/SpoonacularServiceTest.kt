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
package com.beetlestance.aphid.spoonacular.kotlin

import com.beetlestance.aphid.spoonacular.kotlin.services.RecipesService
import com.google.common.truth.Truth.assertWithMessage
import org.junit.Before
import org.junit.Test

class SpoonacularServiceTest {

    lateinit var recipesService: RecipesService

    private val spoonacular = Spoonacular("")
        .retrofitBuilder()
        .build()

    @Before
    fun setUpService() {
        recipesService = spoonacular.create(RecipesService::class.java)
    }

    @Test
    fun validateApiKey() {
        val api = System.getenv().getOrDefault(API_KEY_PARAM, "")
        assertWithMessage("No API Key provided").that(api).isNotEmpty()
    }

    companion object {
        const val API_KEY_PARAM = "TEST_API_KEY"
    }
}
