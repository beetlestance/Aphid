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

internal object MealPlanner {

    object UserName {

        object Items {

            const val ADD_TO_MEAL_PLAN = "/mealplanner/{username}/items"

            const val DELETE_FROM_MEAL_PLAN = "/mealplanner/{username}/items/{id}"
        }

        object Templates {

            const val GET_MEAL_PLAN_TEMPLATE = "/mealplanner/{username}/templates/{id}"

            const val GET_MEAL_PLAN_TEMPLATES = "/mealplanner/{username}/templates"
        }

        object ShoppingLists {

            const val ADD_TO_SHOPPING_LIST = "/mealplanner/{username}/shopping-list/items"

            const val DELETE_FROM_SHOPPING_LIST = "/mealplanner/{username}/shopping-list/items/{id}"

            const val GENERATE_SHOPPING_LIST =
                "/mealplanner/{username}/shopping-list/{start-date}/{end-date}"

            const val GET_SHOPPING_LIST = "/mealplanner/{username}/shopping-list"
        }

        const val GET_MEAL_PLAN_WEEK = "/mealplanner/{username}/week/{start-date}"
    }

    const val GENERATE_MEAL_PLAN = "/mealplanner/generate"

    const val GET_PUBLIC_MEAL_PLAN_TEMPLATES = "/mealplanner/public-templates"
}
