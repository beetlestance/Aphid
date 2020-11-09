package com.beetlestance.aphid

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val iconFilled: Int,
    @DrawableRes val iconOutlined: Int
) {
    object Chat :
        Screen(
            route = "chat",
            resourceId = R.string.feature_chat,
            iconFilled = R.drawable.chat_filled,
            iconOutlined = R.drawable.chat_outline
        )

    object Explore : Screen(
        route = "explore",
        resourceId = R.string.feature_explore,
        iconFilled = R.drawable.explore_filled,
        iconOutlined = R.drawable.explore_outline
    )

    object MealPlanner : Screen(
        route = "mealplanner",
        resourceId = R.string.feature_meal_planner,
        iconFilled = R.drawable.meal_planner_filled,
        iconOutlined = R.drawable.meal_planner_outline
    )

    object Grocery : Screen(
        route = "grocery",
        resourceId = R.string.feature_grocery,
        iconFilled = R.drawable.grocery_filled,
        iconOutlined = R.drawable.grocery_outline
    )

    object Profile : Screen(
        route = "profile",
        resourceId = R.string.feature_profile,
        iconFilled = R.drawable.user_filled,
        iconOutlined = R.drawable.user_outline
    )
}
