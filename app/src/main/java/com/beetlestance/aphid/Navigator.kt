package com.beetlestance.aphid

import androidx.annotation.DrawableRes

sealed class Screen(
    val route: String,
    @DrawableRes val iconFilled: Int,
    @DrawableRes val iconOutlined: Int
) {
    object Chat :
        Screen(
            route = "chat",
            iconFilled = R.drawable.chat_filled,
            iconOutlined = R.drawable.chat_outline
        )

    object Explore : Screen(
        route = "explore",
        iconFilled = R.drawable.explore_filled,
        iconOutlined = R.drawable.explore_outline
    )

    object MealPlanner : Screen(
        route = "mealplanner",
        iconFilled = R.drawable.meal_planner_filled,
        iconOutlined = R.drawable.meal_planner_outline
    )

    object Grocery : Screen(
        route = "grocery",
        iconFilled = R.drawable.grocery_filled,
        iconOutlined = R.drawable.grocery_outline
    )

    object Profile : Screen(
        route = "profile",
        iconFilled = R.drawable.user_filled,
        iconOutlined = R.drawable.user_outline
    )
}
