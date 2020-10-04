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

}
