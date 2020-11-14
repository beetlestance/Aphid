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
package com.beetlestance.aphid

import android.content.res.Configuration
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.beetlestance.aphid.common_compose.AndroidAVDIcon
import com.beetlestance.aphid.common_compose.bottomnavigation.CurvedCutBottomNavigation
import com.beetlestance.aphid.common_compose.bottomnavigation.CurvedCutBottomNavigationItem
import com.beetlestance.aphid.feature_explore.Explore
import com.google.android.material.composethemeadapter.MdcTheme
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.imageloading.toPainter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // disabled dark theme temporarily
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                // Night mode is not active, we're using the light theme
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                // Night mode is active, we're using dark theme
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        resources.configuration.uiMode = Configuration.UI_MODE_NIGHT_NO
        setContent(content = {
            MdcTheme {
                AphidHome()
            }
        })
    }

    @Composable
    fun AphidHome() {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                CurvedCutBottomNavigation(
                    backgroundColor = MaterialTheme.colors.surface,
                    fabBackgroundColor = MaterialTheme.colors.primarySurface,
                    defaultSelection = navItems.indexOf(Screen.Explore),
                    menuItems = navItems.size
                ) { state ->
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                    navItems.forEachIndexed { index, screen ->
                        CurvedCutBottomNavigationItem(
                            icon = {
                                val resId = screen.iconOutlined
                                val color = MaterialTheme.colors.background
                                Icon(asset = vectorResource(id = resId), tint = color)
                            },
                            fabIcon = {
                                val resId = screen.iconFilled
                                val color = MaterialTheme.colors.surface
                                AndroidAVDIcon(drawableId = resId, tint = color)
                            },
                            index = index,
                            state = state,
                            selected = currentRoute == screen.route,
                            onClick = {
                                // This if check gives us a "singleTop" behavior where we do not create a
                                // second instance of the composable if we are already on that destination
                                if (currentRoute != screen.route) {
                                    // This is the equivalent to popUpTo the start destination
                                    navController.popBackStack(
                                        navController.graph.startDestination,
                                        false
                                    )

                                    navController.navigate(screen.route)
                                }
                            }
                        )
                    }
                }
            }
        ) {
            NavHost(navController, startDestination = Screen.Explore.route) {
                composable(Screen.Chat.route) { Profile() }
                composable(Screen.Explore.route) {
                    val viewState by viewModel.liveData.observeAsState()
                    if (viewState != null) {
                        Explore(viewState ?: return@composable) {
                            viewModel.submitAction(it)
                        }
                    }
                }
                composable(Screen.MealPlanner.route) { Profile() }
                composable(Screen.Grocery.route) { Profile() }
                composable(Screen.Profile.route) { Profile() }
            }
            Spacer(modifier = Modifier.preferredHeight(56.dp))
        }
    }

    @Composable
    private fun Profile() {

    }

    private val navItems = listOf(
        Screen.Chat,
        Screen.Explore,
        Screen.MealPlanner,
        Screen.Grocery,
        Screen.Profile
    )
}
