/*
 * Copyright 2021 BeetleStance
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
