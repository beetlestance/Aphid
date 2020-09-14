package com.beetlestance.spoonacular_kotlin.annotations

import androidx.annotation.StringDef
import com.beetlestance.spoonacular_kotlin.constants.SpoonacularImageSize.Equipment
import com.beetlestance.spoonacular_kotlin.constants.SpoonacularImageSize.Grocery
import com.beetlestance.spoonacular_kotlin.constants.SpoonacularImageSize.Ingredient
import com.beetlestance.spoonacular_kotlin.constants.SpoonacularImageSize.MenuItem
import com.beetlestance.spoonacular_kotlin.constants.SpoonacularImageSize.Recipe

@StringDef(Equipment.LOW_QUALITY, Equipment.MEDIUM_QUALITY, Equipment.HIGH_QUALITY)
@Retention(AnnotationRetention.SOURCE)
annotation class EquipmentImageSize

@StringDef(Ingredient.LOW_QUALITY, Ingredient.MEDIUM_QUALITY, Ingredient.HIGH_QUALITY)
@Retention(AnnotationRetention.SOURCE)
annotation class IngredientImageSize

@StringDef(Grocery.LOW_QUALITY, Grocery.MEDIUM_QUALITY, Grocery.HIGH_QUALITY)
@Retention(AnnotationRetention.SOURCE)
annotation class GroceryImageSize

@StringDef(MenuItem.LOW_QUALITY, MenuItem.MEDIUM_QUALITY, MenuItem.HIGH_QUALITY)
@Retention(AnnotationRetention.SOURCE)
annotation class MenuItemImageSize

@StringDef(
    Recipe.ULTRA_LOW_QUALITY,
    Recipe.VERY_LOW_QUALITY,
    Recipe.LOW_QUALITY,
    Recipe.MEDIUM_QUALITY,
    Recipe.HIGH_QUALITY,
    Recipe.VERY_HIGH_QUALITY,
    Recipe.ULTRA_HIGH_QUALITY
)
@Retention(AnnotationRetention.SOURCE)
annotation class RecipeImageSize
