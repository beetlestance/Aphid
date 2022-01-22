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

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.beetlestance.aphid.feature_chat.Chat
import com.beetlestance.aphid.feature_explore.Explore
import com.beetlestance.aphid.feature_profile.Profile

sealed class Screen(val route: String) {
    object Chat : Screen(route = "chat")

    object Explore : Screen(route = "explore")

    object MealPlanner : Screen(route = "mealplanner")

    object Grocery : Screen(route = "grocery")

    object Profile : Screen(route = "profile")
}

@Composable
fun AphidNavigation(
    navController: NavHostController,
    navBarPadding: PaddingValues
) {
    NavHost(navController, startDestination = Screen.Explore.route) {
        composable(Screen.Chat.route) { Chat(paddingValues = navBarPadding) }
        composable(Screen.Explore.route) { Explore(paddingValues = navBarPadding) }
        composable(Screen.MealPlanner.route) { Dummy() }
        composable(Screen.Grocery.route) { Dummy() }
        composable(Screen.Profile.route) { Profile(paddingValues = navBarPadding) }
    }
}
