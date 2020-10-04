package com.beetlestance.spoonacular_kotlin.annotations

import androidx.annotation.StringDef
import com.beetlestance.spoonacular_kotlin.constants.MealPlanType


@StringDef(
    MealPlanType.CUSTOM_FOOD,
    MealPlanType.INGREDIENTS,
    MealPlanType.MENU_ITEM,
    MealPlanType.PRODUCT,
    MealPlanType.RECIPE,
)
@Retention(AnnotationRetention.SOURCE)
annotation class MealPlanType
