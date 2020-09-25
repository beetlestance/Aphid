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

internal object Food {

    object Converse {

        const val GET_CONVERSATION_SUGGESTS = "/food/converse/suggest"

        const val TALK_TO_CHATBOT = "/food/converse"
    }

    object Images {

        const val IMAGE_CLASSIFICATION_BY_URL = "/food/images/analyze"

        const val IMAGE_ANALYSIS_BY_URL = "/food/images/classify"
    }

    object Ingredients {

        object ById {

            const val GET_INGREDIENT_INFORMATION = "/food/ingredients/{id}/information"

            const val GET_INGREDIENT_SUBSTITUTES = "/food/ingredients/{id}/substitutes"
        }

        const val AUTOCOMPLETE_INGREDIENT_SEARCH = "/food/ingredients/autocomplete"

        const val GET_INGREDIENT_SUBSTITUTES = "/food/ingredients/substitutes"

        const val MAP_INGREDIENTS_TO_GROCERY_PRODUCTS = "/food/ingredients/map"
    }

    object MenuItems {

        object ById {

            const val GET_MENU_ITEM_INFORMATION = "/food/menuItems/{id}"

            const val VISUALIZE_MENU_ITEM_NUTRITION = "/food/menuItems/{id}/nutritionWidget"
        }

        const val AUTOCOMPLETE_MENU_ITEM_SEARCH = "/food/menuItems/suggest"

        const val SEARCH_MENU_ITEMS = "/food/menuItems/search"
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

        const val CLASSIFY_GROCERY_PRODUCT = "/food/products/classify"

        const val CLASSIFY_GROCERY_PRODUCT_BULK = "/food/products/classifyBatch"

        const val SEARCH_GROCERY_PRODUCTS = "/food/products/search"
    }

    object Wine {

        const val GET_DISH_PAIRING_FOR_WINE = "/food/wine/dishes"

        const val GET_WINE_DESCRIPTION = "/food/wine/description"

        const val GET_WINE_PAIRING = "/food/wine/pairing"

        const val GET_WINE_RECOMMENDATION = "/food/wine/recommendation"
    }

    const val GET_A_RANDOM_FOOD_JOKE = "/food/jokes/random"

    const val GET_RANDOM_FOOD_TRIVIA = "/food/trivia/random"

    const val DETECT_FOOD_IN_TEXT = "/food/detect"

    const val SEARCH_CUSTOM_FOODS = "/food/customFoods/search"

    const val SEARCH_FOOD_VIDEOS = "/food/videos/search"

    const val SEARCH_SITE_CONTENT = "/food/site/search"
}
