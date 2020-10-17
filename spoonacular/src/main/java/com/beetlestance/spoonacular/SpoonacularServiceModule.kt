package com.beetlestance.spoonacular

import com.beetlestance.spoonacular_kotlin.Spoonacular
import com.beetlestance.spoonacular_kotlin.services.FoodService
import com.beetlestance.spoonacular_kotlin.services.MealPlannerService
import com.beetlestance.spoonacular_kotlin.services.RecipesService
import com.beetlestance.spoonacular_kotlin.services.UserService
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
