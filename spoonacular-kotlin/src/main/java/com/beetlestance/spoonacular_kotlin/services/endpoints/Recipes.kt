/*
 * Copyright 2020 BeetleStance
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
package com.beetlestance.spoonacular_kotlin.services.endpoints

internal object Recipes {

    object ById {

        const val GET_ANALYZED_RECIPE_INSTRUCTIONS = "/recipes/{id}/analyzedInstructions"

        const val GET_RECIPE_EQUIPMENT = "/recipes/{id}/equipmentWidget.json"

        const val GET_RECIPE_INFORMATION = "/recipes/{id}/information"

        const val GET_RECIPE_INGREDIENTS = "/recipes/{id}/ingredientWidget.json"

        const val GET_RECIPE_NUTRITION = "/recipes/{id}/nutritionWidget.json"

        const val GET_RECIPE_PRICE_BREAKDOWN = "/recipes/{id}/priceBreakdownWidget.json"

        const val GET_SIMILAR_RECIPES = "/recipes/{id}/similar"

        const val SUMMARIZE_RECIPE = "/recipes/{id}/summary"

        const val VISUALIZE_RECIPE_EQUIPMENT = "/recipes/{id}/equipmentWidget"

        const val VISUALIZE_RECIPE_INGREDIENTS = "/recipes/{id}/ingredientWidget"

        const val VISUALIZE_RECIPE_NUTRITION = "/recipes/{id}/nutritionWidget"

        const val VISUALIZE_RECIPE_PRICE_BREAKDOWN = "/recipes/{id}/priceBreakdownWidget"
    }

    const val ANALYZE_A_RECIPE_SEARCH_QUERY = "/recipes/queries/analyze"

    const val ANALYZE_RECIPE_INSTRUCTIONS = "/recipes/analyzeInstructions"

    const val AUTOCOMPLETE_RECIPE_SEARCH = "/recipes/autocomplete"

    const val CLASSIFY_CUISINE = "/recipes/cuisine"

    const val CONVERT_AMOUNTS = "/recipes/convert"

    const val CREATE_RECIPE_CARD = "/recipes/visualizeRecipe"

    const val EXTRACT_RECIPE_FROM_WEBSITE = "/recipes/extract"

    const val GET_RANDOM_RECIPES = "/recipes/random"

    const val GET_RECIPE_INFORMATION_BULK = "/recipes/informationBulk"

    const val GUESS_NUTRITION_BY_DISH_NAME = "/recipes/guessNutrition"

    const val PARSE_INGREDIENTS = "/recipes/parseIngredients"

    const val QUICK_ANSWER = "/recipes/quickAnswer"

    const val SEARCH_RECIPES = "/recipes/search"

    const val SEARCH_RECIPES_BY_INGREDIENTS = "/recipes/findByIngredients"

    const val SEARCH_RECIPES_BY_NUTRIENTS = "/recipes/findByNutrients"

    const val SEARCH_RECIPES_COMPLEX = "/recipes/complexSearch"

    const val VISUALIZE_EQUIPMENT = "/recipes/visualizeEquipment"

    const val VISUALIZE_INGREDIENTS = "/recipes/visualizeIngredients"

    const val VISUALIZE_PRICE_BREAKDOWN = "/recipes/visualizePriceEstimator"

    const val VISUALIZE_RECIPE_NUTRITION = "/recipes/visualizeNutrition"
}
