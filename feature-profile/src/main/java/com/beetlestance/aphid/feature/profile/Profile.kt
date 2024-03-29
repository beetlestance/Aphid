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
package com.beetlestance.aphid.feature.profile

import androidx.annotation.StringRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.Indicator
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.beetlestance.aphid.common.compose.RecipeDetailedPosterCard
import com.beetlestance.aphid.data.entities.Recipe
import com.beetlestance.aphid.dicebar.kotlin.DiceBarAvatarHelper
import com.beetlestance.aphid.dicebar.kotlin.sprites.avataar.AvataaarTop
import com.beetlestance.aphid.dicebar.kotlin.sprites.avataar.AvataaarsConfig
import com.beetlestance.aphid.dicebar.kotlin.sprites.avataar.AvataaarsSprite
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.math.abs

private val PROFILE_CONTENT_PADDING = 16.dp

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun Profile(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues()
) {
    val viewModel: ProfileViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Profile(
        modifier = modifier.fillMaxSize(),
        state = state,
        contentPadding = paddingValues,
        markRecipeAsFavourite = { recipe, isFavourite ->
            viewModel.markRecipeAsFavourite(recipe, isFavourite)
        }
    )
}

@Composable
private fun Profile(
    state: ProfileViewState,
    contentPadding: PaddingValues,
    markRecipeAsFavourite: (Recipe, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Surface(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
            ProfileSection(
                modifier = Modifier.padding(PROFILE_CONTENT_PADDING),
                avatarUrl = DiceBarAvatarHelper.createAvatarUrl(
                    sprite = AvataaarsSprite(),
                    seed = "Male",
                    config = AvataaarsConfig(
                        top = listOf(AvataaarTop.EYE_PATCH, AvataaarTop.HAT),
                        accessoriesChance = 93f
                    )
                ).toString()
            )
        }

        ProfileRecipesTabLayout(
            paddingValues = contentPadding,
            favouriteRecipes = state.favouriteRecipes,
            savedRecipes = state.savedRecipes,
            markRecipeAsFavourite = { recipe, isFavourite ->
                markRecipeAsFavourite(recipe, isFavourite)
            }
        )
    }
}

@Composable
private fun ProfileSection(
    modifier: Modifier = Modifier,
    avatarUrl: String
) {
    Box(modifier = modifier, contentAlignment = Alignment.CenterStart) {
        ProfileAvatar(modifier = Modifier, avatarUrl = avatarUrl)

        ProfileDetails(
            modifier = Modifier.offset(x = PROFILE_SHAPE_SIZE + 16.dp),
            userName = "Akshay Yadav"
        )

        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = {}
        ) {
            Icon(imageVector = Icons.Filled.Settings, contentDescription = "Settings")
        }
    }
}

private val PROFILE_SHAPE_SIZE = 72.dp
private val PROFILE_SHAPE_ELEVATION = 2.dp

@Composable
private fun svgImageLoader(): ImageLoader {
    return ImageLoader.Builder(LocalContext.current)
        .components { add(SvgDecoder.Factory()) }
        .build()
}

@Composable
private fun ProfileAvatar(
    modifier: Modifier = Modifier,
    avatarUrl: String
) {
    Surface(
        modifier = modifier.size(PROFILE_SHAPE_SIZE),
        shape = CircleShape,
        elevation = PROFILE_SHAPE_ELEVATION
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(avatarUrl)
                    .crossfade(true)
                    .build(),
                imageLoader = svgImageLoader()
            ),
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds(),
            contentScale = ContentScale.Crop,
            contentDescription = "User Avatar"
        )
    }
}

private val EDIT_PROFILE_BUTTON_RADIUS = 16.dp

@Composable
private fun ProfileDetails(
    modifier: Modifier = Modifier,
    userName: String,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp,
            alignment = Alignment.Top
        )
    ) {
        Text(
            text = userName,
            style = MaterialTheme.typography.h5
        )

        OutlinedButton(
            onClick = onClick,
            shape = RoundedCornerShape(EDIT_PROFILE_BUTTON_RADIUS)
        ) {
            Text(
                text = stringResource(R.string.profile_action_edit_profile),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.button
            )
        }
    }
}

private enum class RecipesTabs(@StringRes val resId: Int) {
    SAVED(R.string.profile_tab_saved_recipe),
    FAVOURITE(R.string.profile_tab_favourite_recipe)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ProfileRecipesTabLayout(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    favouriteRecipes: List<Recipe>,
    savedRecipes: List<Recipe>,
    markRecipeAsFavourite: (Recipe, Boolean) -> Unit
) {
    val tabsResId = remember { RecipesTabs.values().map { it.resId } }
    val pagerState = rememberPagerState(initialPage = RecipesTabs.SAVED.ordinal)

    RecipeTab(modifier = modifier, pagerState = pagerState, tabsResId = tabsResId)

    RecipePager(
        state = pagerState,
        paddingValues = paddingValues,
        savedRecipes = savedRecipes,
        favouriteRecipes = favouriteRecipes,
        markRecipeAsFavourite = markRecipeAsFavourite
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun RecipeTab(
    modifier: Modifier,
    pagerState: PagerState,
    tabsResId: List<Int>
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            with(pagerState) {
                val tabPosition = tabPositions[currentPage]
                // CurrentPageOffset is always in -1f to 1f,As tab width is pageSize / itemSize,
                // i.e for 2 items width offset for given page offset absolute value will always be
                // in 0 to 0.5 f therefore to calculate tabOffset for given page offset subtract
                // currentPageOffset from current page
                val tabOffsetPercentage = currentPage - currentPageOffset
                // tabOffsetPercentage is dragPosition divided by pageSize, for offset multiply
                // given percentage with tabSize
                val tabOffset = tabPosition.width.times(abs(tabOffsetPercentage))
                val scrollStateIdle = currentPageOffset == 0f

                return@with Indicator(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.BottomStart)
                        .offset(x = if (scrollStateIdle) tabPosition.left else tabOffset)
                        .width(tabPosition.width)
                )
            }
        },
        backgroundColor = MaterialTheme.colors.surface
    ) {
        val coroutineScope = rememberCoroutineScope()
        tabsResId.forEachIndexed { index, resId ->
            Tab(
                selected = index == pagerState.currentPage,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = { Text(text = stringResource(resId)) }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun RecipePager(
    modifier: Modifier = Modifier,
    savedRecipes: List<Recipe>,
    favouriteRecipes: List<Recipe>,
    paddingValues: PaddingValues,
    markRecipeAsFavourite: (Recipe, Boolean) -> Unit,
    state: PagerState
) {
    val animatedSet = remember { mutableSetOf<Int>() }

    HorizontalPager(
        modifier = modifier,
        count = savedRecipes.size,
        state = state,
        contentPadding = PaddingValues(PROFILE_CONTENT_PADDING),
        itemSpacing = 16.dp,
        key = { index -> savedRecipes[index].recipeId }
    ) { page ->
        when (page) {
            RecipesTabs.SAVED.ordinal -> SavedRecipes(
                savedRecipes = savedRecipes,
                paddingValues = paddingValues,
                animatedCards = animatedSet,
                markRecipeAsFavourite = markRecipeAsFavourite
            )
            RecipesTabs.FAVOURITE.ordinal -> FavouriteRecipes(
                favouriteRecipes = favouriteRecipes,
                paddingValues = paddingValues,
                markRecipeAsFavourite = markRecipeAsFavourite
            )
        }
    }
}

private const val EMPTY_URL = ""
private val RECIPE_CARD_TRANSITION = TweenSpec<Float>(
    durationMillis = 400,
    delay = 50,
    easing = LinearEasing
)

@Composable
private fun SavedRecipes(
    modifier: Modifier = Modifier,
    savedRecipes: List<Recipe>,
    paddingValues: PaddingValues,
    animatedCards: MutableSet<Int>,
    markRecipeAsFavourite: (Recipe, Boolean) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = paddingValues,
        content = {
            itemsIndexed(
                items = savedRecipes,
                key = { _, item -> item.recipeId }
            ) { index, recipe ->
                val coroutineScope = rememberCoroutineScope()
                val offsetValue = remember {
                    Animatable(initialValue = if (index in animatedCards) 0F else 150F)
                }
                val alphaValue = remember {
                    Animatable(initialValue = if (index in animatedCards) 1F else 0F)
                }

                LaunchedEffect(recipe.id) {
                    offsetValue.animateTo(0F, animationSpec = RECIPE_CARD_TRANSITION)
                    alphaValue.animateTo(1F, animationSpec = RECIPE_CARD_TRANSITION)
                    animatedCards.add(index)
                }

                DisposableEffect(recipe.id) {
                    onDispose {
                        coroutineScope.launch {
                            offsetValue.snapTo(0F)
                            offsetValue.stop()
                        }
                    }
                }

                SavedRecipeCard(
                    modifier = Modifier
                        .offset(y = offsetValue.value.toInt().dp)
                        .alpha(alphaValue.value),
                    markRecipeAsFavourite = markRecipeAsFavourite,
                    recipe = recipe
                )
            }
        }
    )
}

@Composable
private fun SavedRecipeCard(
    modifier: Modifier = Modifier,
    markRecipeAsFavourite: (Recipe, Boolean) -> Unit,
    recipe: Recipe
) {
    RecipeDetailedPosterCard(
        modifier = modifier,
        isFavourite = recipe.isFavourite ?: false,
        onCheckedChange = { isChecked -> markRecipeAsFavourite(recipe, isChecked) },
        posterImage = {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data = recipe.imageUrl ?: EMPTY_URL)
                        .crossfade(true)
                        .build()
                ),
                modifier = Modifier.aspectRatio(1.5f),
                contentScale = ContentScale.Crop,
                contentDescription = "Saved Recipes"
            )
        },
        posterDetails = {
            RecipeDetails(
                name = recipe.title ?: "",
                contentTags = "1 Serving • 20 Min • 205 Cal",
                rating = "4.4"
            )
            // Item Spacing
            Spacer(modifier = Modifier.height(16.dp))
        }
    )
}

private const val GRID_ITEM_COUNT = 2
private val GRID_ITEM_SPACING = 8.dp

@Composable
private fun FavouriteRecipes(
    modifier: Modifier = Modifier,
    favouriteRecipes: List<Recipe>,
    paddingValues: PaddingValues,
    markRecipeAsFavourite: (Recipe, Boolean) -> Unit
) {
    val animatedSet = remember { mutableSetOf<Int>() }

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(GRID_ITEM_COUNT),
        contentPadding = paddingValues,
        content = {
            itemsIndexed(favouriteRecipes) { index, recipe ->
                val coroutineScope = rememberCoroutineScope()
                val offsetValue = remember {
                    Animatable(initialValue = if (index in animatedSet) 0F else 150F)
                }
                val alphaValue = remember {
                    Animatable(initialValue = if (index in animatedSet) 1F else 0F)
                }

                LaunchedEffect(recipe.id) {
                    offsetValue.animateTo(0F, animationSpec = RECIPE_CARD_TRANSITION)
                    alphaValue.animateTo(1F, animationSpec = RECIPE_CARD_TRANSITION)
                    animatedSet.add(index)
                }

                DisposableEffect(recipe.id) {
                    onDispose {
                        coroutineScope.launch {
                            offsetValue.snapTo(0F)
                            offsetValue.stop()
                        }
                    }
                }

                val cellPadding = if (index % 2 == 0) PaddingValues(end = GRID_ITEM_SPACING)
                else PaddingValues(start = GRID_ITEM_SPACING)

                FavouriteRecipeCard(
                    modifier = modifier.padding(cellPadding),
                    markRecipeAsFavourite = markRecipeAsFavourite,
                    recipe = recipe
                )
            }
        }
    )
}

@Composable
private fun FavouriteRecipeCard(
    modifier: Modifier = Modifier,
    markRecipeAsFavourite: (Recipe, Boolean) -> Unit,
    recipe: Recipe
) {
    RecipeDetailedPosterCard(
        modifier = modifier,
        isFavourite = recipe.isFavourite ?: false,
        onCheckedChange = { isChecked -> markRecipeAsFavourite(recipe, isChecked) },
        posterImage = {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data = recipe.imageUrl ?: EMPTY_URL)
                        .crossfade(true)
                        .build()
                ),
                modifier = Modifier.aspectRatio(0.7f),
                contentScale = ContentScale.Crop,
                contentDescription = "Favourite Recipes"
            )
        },
        posterDetails = {
            RecipeDetails(
                name = recipe.title ?: "",
                contentTags = "2 Serving • 20 Min • 205 Cal",
                rating = "4.6"
            )
            // Item Spacing
            Spacer(modifier = Modifier.height(16.dp))
        }
    )
}

@Composable
private fun RecipeDetails(
    name: String,
    rating: String,
    contentTags: String
) {
    FlowRow(
        mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
        mainAxisSize = SizeMode.Expand,
        crossAxisAlignment = FlowCrossAxisAlignment.Center,
        crossAxisSpacing = 4.dp
    ) {
        Text(
            text = contentTags,
            style = MaterialTheme.typography.body2,
            color = contentColorFor(backgroundColor = MaterialTheme.colors.surface).copy(alpha = 0.7f)
        )

        Text(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors.primarySurface,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 12.dp, vertical = 2.dp),
            text = rating,
            style = MaterialTheme.typography.subtitle2,
            color = contentColorFor(backgroundColor = MaterialTheme.colors.primarySurface)
        )
    }

    Text(
        text = name,
        style = MaterialTheme.typography.body1,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colors.onSurface,
        maxLines = 1
    )
}
