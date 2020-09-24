package com.beetlestance.spoonacular_kotlin.services.endpoints

internal object Food {

    object Ingredients {

        object ById {

            const val GET_INGREDIENT_INFORMATION = "/food/ingredients/{id}/information"

            const val GET_INGREDIENT_SUBSTITUTES = "/food/ingredients/{id}/substitutes"
        }

        const val AUTOCOMPLETE_INGREDIENT_SEARCH = "/food/ingredients/autocomplete"

        const val GET_INGREDIENT_SUBSTITUTES = "/food/ingredients/substitutes"

        const val MAP_INGREDIENTS_TO_GROCERY_PRODUCTS = "/food/ingredients/map"
    }
}
