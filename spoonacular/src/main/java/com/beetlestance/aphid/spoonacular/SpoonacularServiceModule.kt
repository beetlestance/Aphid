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
package com.beetlestance.aphid.spoonacular

import com.beetlestance.aphid.spoonacular.kotlin.Spoonacular
import com.beetlestance.aphid.spoonacular.kotlin.services.FoodService
import com.beetlestance.aphid.spoonacular.kotlin.services.MealPlannerService
import com.beetlestance.aphid.spoonacular.kotlin.services.RecipesService
import com.beetlestance.aphid.spoonacular.kotlin.services.UserService
import dagger.Module
import dagger.Provides

@Module
object SpoonacularServiceModule {

    @Provides
    fun provideUserService(spoonacular: Spoonacular): UserService =
        spoonacular.createUserService()

    @Provides
    fun provideFoodService(spoonacular: Spoonacular): FoodService =
        spoonacular.createFoodService()

    @Provides
    fun provideMealPlannerService(spoonacular: Spoonacular): MealPlannerService =
        spoonacular.createMealPlannerService()

    @Provides
    fun provideRecipeService(spoonacular: Spoonacular): RecipesService =
        spoonacular.createRecipeService()
}
