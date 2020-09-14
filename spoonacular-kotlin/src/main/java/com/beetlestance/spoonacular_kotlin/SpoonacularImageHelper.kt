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
@file:Suppress("unused")

package com.beetlestance.spoonacular_kotlin

import com.beetlestance.spoonacular_kotlin.annotations.EquipmentImageSize
import com.beetlestance.spoonacular_kotlin.annotations.GroceryImageSize
import com.beetlestance.spoonacular_kotlin.annotations.IngredientImageSize
import com.beetlestance.spoonacular_kotlin.annotations.MenuItemImageSize
import com.beetlestance.spoonacular_kotlin.annotations.RecipeImageSize
import com.beetlestance.spoonacular_kotlin.constants.SpoonacularImageSize
import java.math.BigDecimal

object SpoonacularImageHelper {
    /**
     *   The base path for image URLs
     */
    private const val IMAGE_URL: String = "https://spoonacular.com"

    /**
     *   The base path for image URLs for menu items
     */
    private const val MENU_ITEMS_IMAGE_URL: String = "https://images.spoonacular.com"

    /**
     * The base path for equipment images is https://spoonacular.com/cdn/equipment_{SIZE}/{NAME}
     * */
    private const val EQUIPMENT: String = "/cdn/equipment_"

    /**
     * The base path for ingredient images is https://spoonacular.com/cdn/ingredients_{SIZE}/{NAME}
     * */
    private const val INGREDIENTS: String = "/cdn/ingredients_"

    /**
     * The base path for grocery images is https://spoonacular.com/productImages/{ID}-{SIZE}.{TYPE}
     * */
    private const val GROCERY: String = "/productImages"

    /**
     * The base path for menu items images is
     * https://images.spoonacular.com/file/wximages/{ID}-{SIZE}.{TYPE}
     * */
    private const val MENU_ITEMS: String = "/file/wximages"

    /**
     * The base path for recipe images is https://spoonacular.com/recipeImages/{ID}-{SIZE}.{TYPE}
     * */
    private const val RECIPE: String = "/recipeImages"

    /**
     * The recipe instruction endpoint will give you information about the equipment used for
     * cooking the dish. You will only receive the image name for the equipment.
     * You have to build the full URL by adding the base path to the beginning.
     * The base path for equipment images is https://spoonacular.com/cdn/equipment_{SIZE}/{NAME},
     * where {SIZE} is one of the following:
     *
     * @param imageSize Size of image
     * @param imageName Name of image (ex slow-cooker.jpg)
     * @return String
     * @see SpoonacularImageSize.Equipment
     * */
    fun generateEquipmentImageUrl(
        @EquipmentImageSize imageSize: String,
        imageName: String
    ): String {
        return "$IMAGE_URL$EQUIPMENT$imageSize/$imageName"
    }

    /**
     * Endpoints like the ingredient autosuggestion will only give you an image name.
     * You have to build the full URL by adding the base path to the beginning.
     * The base path for ingredient images is https://spoonacular.com/cdn/ingredients_{SIZE}/{NAME},
     * where {SIZE} is one of the following:
     *
     * @param imageSize Size of image
     * @param imageName Name of image (ex apple.jpg)
     * @return String
     * @see SpoonacularImageSize.Ingredient
     * */
    fun generateIngredientImageUrl(
        @IngredientImageSize imageSize: String,
        imageName: String
    ): String {
        return "$IMAGE_URL$INGREDIENTS$imageSize/$imageName"
    }

    /**
     * Grocery product endpoints will almost always give you a product id {ID}.
     * With that and the imageType {TYPE} you can build the complete image paths depending
     * on your needs.The base path for image URLs is https://spoonacular.com/productImages/.
     * Once you know the product id {ID} and image type {TYPE}, you can complete that path to
     * show an image. The complete path follows this pattern
     * https://spoonacular.com/productImages/{ID}-{SIZE}.{TYPE},where {SIZE} is one of the following
     *
     * @param id Id of grocery product
     * @param imageSize Size of image
     * @param imageType File type of image (ex .jpg)
     * @return String
     * @see SpoonacularImageSize.Grocery
     * */
    fun generateGroceryImageUrl(
        id: BigDecimal,
        @GroceryImageSize imageSize: String,
        imageType: String
    ): String {
        return "$IMAGE_URL$GROCERY/$id-$imageSize.$imageType"
    }

    /**
     * Menu item will almost always give you a menu item id {ID}.
     * With that and the imageType {TYPE} you can build the complete image paths depending on your needs.
     * The base path for image URLs is https://images.spoonacular.com/file/wximages/.
     * Once you know the menu item id {ID} and image type {TYPE}, you can complete that path to show
     * an image.The complete path follows this pattern
     * https://images.spoonacular.com/file/wximages/{ID}-{SIZE}.{TYPE},
     * where {SIZE} is one of the following:
     *
     * @param id Id of menu item
     * @param imageSize Size of image
     * @param imageType File type of image (ex .jpg)
     * @return String
     * @see SpoonacularImageSize.MenuItem
     * */
    fun generateMenuItemsImageUrl(
        id: BigDecimal,
        @MenuItemImageSize imageSize: String,
        imageType: String
    ): String {
        return "$MENU_ITEMS_IMAGE_URL$MENU_ITEMS/$id-$imageSize.$imageType"
    }

    /**
     * Recipe endpoints will almost always give you a recipe id {ID}.
     * With that and the imageType {TYPE} you can build the complete image paths depending on
     * your needs.The base path for image URLs is https://spoonacular.com/recipeImages/.
     * Once you know the recipe id {ID} and image type {TYPE}, you can complete that path to show an
     * image. The complete path follows this pattern
     * https://spoonacular.com/recipeImages/{ID}-{SIZE}.{TYPE}, where {SIZE} is one of the following
     *
     * @param id Id of recipe
     * @param imageSize Size of image
     * @param imageType File type of image (ex .jpg)
     * @return String
     * @see SpoonacularImageSize.Recipe
     * */
    fun generateRecipeImageUrl(
        id: BigDecimal,
        @RecipeImageSize imageSize: String,
        imageType: String
    ): String {
        return "$IMAGE_URL$RECIPE/$id-$imageSize.$imageType"
    }
}
