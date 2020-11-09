package com.beetlestance.aphid

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.beetlestance.aphid.feature_explore.Explore
import com.beetlestance.aphid.feature_explore.ExploreActions
import com.beetlestance.aphid.feature_explore.ExploreViewState

@Composable
fun AphidHome(
    state: ExploreViewState,
    action: (ExploreActions) -> Unit
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.surface
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                navItems.forEach { screen ->
                    val isSelected = currentRoute == screen.route
                    BottomNavigationItem(
                        icon = {
                            val resId = if (isSelected) screen.iconFilled else screen.iconOutlined
                            val color = if (isSelected) MaterialTheme.colors.primarySurface
                            else MaterialTheme.colors.background

                            Icon(asset = vectorResource(id = resId), tint = color)
                        },
                        selected = isSelected,
                        onClick = {
                            // This is the equivalent to popUpTo the start destination
                            navController.popBackStack(navController.graph.startDestination, false)

                            // This if check gives us a "singleTop" behavior where we do not create a
                            // second instance of the composable if we are already on that destination
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route)
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(navController, startDestination = Screen.Explore.route) {
            composable(Screen.Chat.route) { Profile(navController) }
            composable(Screen.Explore.route) { Explore(state, action) }
            composable(Screen.MealPlanner.route) { Profile(navController) }
            composable(Screen.Grocery.route) { Profile(navController) }
            composable(Screen.Profile.route) { Profile(navController) }
        }
    }
}

@Composable
private fun Profile(navController: NavController) {

}

private val navItems = listOf(
    Screen.Chat,
    Screen.Explore,
    Screen.MealPlanner,
    Screen.Grocery,
    Screen.Profile
)
