package com.beetlestance.aphid

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.beetlestance.aphid.common_compose.AndroidIcon
import com.beetlestance.aphid.common_compose.bottomnavigation.CurveCutMenuItem
import com.beetlestance.aphid.common_compose.bottomnavigation.CurveCutNavBar
import com.google.accompanist.insets.navigationBarsPadding

@Composable
fun AphidHome() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            val currentSelectedScreen by navController.currentScreenAsState()
            HomeBottomNavigation(
                currentSelectedScreen = currentSelectedScreen,
                navController = navController
            )
        }
    ) { navBarPadding ->
        AphidNavigation(
            navController = navController,
            navBarPadding = navBarPadding
        )
    }
}

@Composable
private fun HomeBottomNavigation(
    currentSelectedScreen: Screen,
    navController: NavController
) {
    CurveCutNavBar(
        modifier = Modifier.navigationBarsPadding(),
        backgroundColor = MaterialTheme.colors.background,
        fabBackgroundColor = MaterialTheme.colors.primary,
        selectedItem = HomeBottomNavigationItems.indexOfFirst { it.screen == currentSelectedScreen },
        maxItems = HomeBottomNavigationItems.size,
        fabIcon = {
            val resId = HomeBottomNavigationItems.elementAt(selectedId).selectedIconRes
            val color = MaterialTheme.colors.surface
            AndroidIcon(drawableId = resId, tint = color)
        }
    ) {
        HomeBottomNavigationItems.forEachIndexed { index, navigationItem ->
            CurveCutMenuItem(
                content = {
                    val resId = navigationItem.iconRes
                    Icon(
                        imageVector = ImageVector.vectorResource(id = resId),
                        contentDescription = "SelectedItem"
                    )
                },
                index = index,
                selected = currentSelectedScreen == navigationItem.screen,
                onClick = {
                    navController.navigate(navigationItem.screen.route) {
                        launchSingleTop = true
                        restoreState = true

                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                }
            )
        }
    }
}

/**
 * Adds an [NavController.OnDestinationChangedListener] to this [NavController] and updates the
 * returned [State] which is updated as the destination changes.
 *
 * This helps us to maintain state even if user navigates via deeplink
 */
@Stable
@Composable
private fun NavController.currentScreenAsState(): State<Screen> {
    val selectedItem = remember { mutableStateOf<Screen>(Screen.Explore) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.route == Screen.Explore.route } -> {
                    selectedItem.value = Screen.Explore
                }
                destination.hierarchy.any { it.route == Screen.Chat.route } -> {
                    selectedItem.value = Screen.Chat
                }
                destination.hierarchy.any { it.route == Screen.Profile.route } -> {
                    selectedItem.value = Screen.Profile
                }
                destination.hierarchy.any { it.route == Screen.MealPlanner.route } -> {
                    selectedItem.value = Screen.MealPlanner
                }
                destination.hierarchy.any { it.route == Screen.Grocery.route } -> {
                    selectedItem.value = Screen.Grocery
                }
            }
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}

@Composable
fun Dummy() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = 124.dp)
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

private data class HomeNavigationItem(
    val screen: Screen,
    @DrawableRes val iconRes: Int,
    @DrawableRes val selectedIconRes: Int
)

private val HomeBottomNavigationItems = listOf(
    HomeNavigationItem(
        screen = Screen.Chat,
        iconRes = R.drawable.chat_outline,
        selectedIconRes = R.drawable.chat_filled
    ),
    HomeNavigationItem(
        screen = Screen.Explore,
        iconRes = R.drawable.explore_outline,
        selectedIconRes = R.drawable.explore_filled
    ),
    HomeNavigationItem(
        screen = Screen.MealPlanner,
        iconRes = R.drawable.meal_planner_outline,
        selectedIconRes = R.drawable.meal_planner_filled
    ),
    HomeNavigationItem(
        screen = Screen.Grocery,
        iconRes = R.drawable.grocery_outline,
        selectedIconRes = R.drawable.grocery_filled
    ),
    HomeNavigationItem(
        screen = Screen.Profile,
        iconRes = R.drawable.user_outline,
        selectedIconRes = R.drawable.user_filled
    )
)