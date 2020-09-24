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

    object Products {

        object ById {

            const val GET_PRODUCT_INFORMATION = "/food/products/{id}"

            const val VISUALIZE_PRODUCT_NUTRITION = "/food/products/{id}/nutritionWidget"

        }

        object ByUPC {

            const val GET_COMPARABLE_PRODUCTS = "/food/products/upc/{upc}/comparable"

            const val SEARCH_GROCERY_PRODUCTS = "/food/products/upc/{upc}"

        }

        const val SEARCH_GROCERY_PRODUCTS = "/food/products/search"

        const val CLASSIFY_GROCERY_PRODUCT = "/food/products/classify"

        const val CLASSIFY_GROCERY_PRODUCT_BULK = "/food/products/classifyBatch"
    }
}
