package com.beetlestance.aphid.feature_profile

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.foundation.layout.FlowCrossAxisAlignment
import androidx.compose.foundation.layout.FlowMainAxisAlignment
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.SizeMode
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.decode.SvgDecoder
import com.beetlestance.aphid.common_compose.RecipeDetailedPosterCard
import com.beetlestance.aphid.common_compose.VerticalGrid
import com.beetlestance.aphid.common_compose.pager.Pager
import com.beetlestance.aphid.common_compose.pager.rememberPagerState
import com.beetlestance.aphid.data.entities.Recipe
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun Profile(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel
) {
    val state by viewModel.liveData.observeAsState(initial = viewModel.currentState())
    val actions: (ProfileActions) -> Unit = { action -> viewModel.submitAction(action) }

    Profile(
        modifier = modifier.fillMaxSize(),
        state = state,
        actions = actions
    )
}

@Composable
private fun Profile(
    modifier: Modifier = Modifier,
    state: ProfileViewState,
    actions: (ProfileActions) -> Unit
) {
    Column(
        modifier = modifier.background(MaterialTheme.colors.surface.copy(alpha = 0.95f))
    ) {
        Surface(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
            ProfileSection(modifier = Modifier.padding(PROFILE_LAYOUT_MARGIN))
        }

        ProfileRecipesTab(
            favouriteRecipes = state.favouriteRecipes,
            savedRecipes = state.savedRecipes,
            markRecipeAsFavourite = { recipe, isFavourite ->
                actions(MarkFavourite(recipe, isFavourite))
            }
        )
    }
}

@Composable
private fun ProfileSection(
    modifier: Modifier = Modifier,
    avatarUrl: String = "https://avatars.dicebear.com/api/avataaars/example.svg?options%5Btop%5D%5B%5D=shortHair&options%5BaccessoriesChance%5D=93"
) {
    Box(modifier = modifier, alignment = Alignment.CenterStart) {

        ProfileAvatar(modifier = Modifier, avatarUrl = avatarUrl)

        ProfileDetails(
            modifier = Modifier.offset(x = PROFILE_SHAPE_SIZE + 16.dp),
            userName = "Akshay Yadav"
        )

        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = {}
        ) {
            Icon(asset = Icons.Filled.Settings)
        }
    }
}


private val PROFILE_SHAPE_SIZE = 72.dp
private val PROFILE_SHAPE_ELEVATION = 2.dp

@Composable
private fun svgImageLoader(): ImageLoader {
    return ImageLoader.Builder(ContextAmbient.current)
        .componentRegistry {
            add(SvgDecoder(ContextAmbient.current))
        }
        .build()
}

@Composable
private fun ProfileAvatar(
    modifier: Modifier = Modifier,
    avatarUrl: String,
) {
    Surface(
        modifier = modifier.size(PROFILE_SHAPE_SIZE),
        shape = CircleShape,
        elevation = PROFILE_SHAPE_ELEVATION
    ) {
        CoilImage(
            modifier = Modifier.fillMaxSize().clipToBounds(),
            data = avatarUrl,
            fadeIn = true,
            imageLoader = svgImageLoader(),
            contentScale = ContentScale.Crop
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
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(
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

@Composable
private fun ProfileRecipesTab(
    modifier: Modifier = Modifier,
    favouriteRecipes: List<Recipe>,
    savedRecipes: List<Recipe>,
    markRecipeAsFavourite: (Recipe, Boolean) -> Unit
) {
    val tabsResId = remember { RecipesTabs.values().map { it.resId } }
    val selectedIndex = remember { mutableStateOf(RecipesTabs.SAVED.ordinal) }

    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedIndex.value,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        tabsResId.forEachIndexed { index, resId ->
            Tab(
                selected = index == selectedIndex.value,
                onClick = { selectedIndex.value = index },
                text = { Text(text = stringResource(resId)) }
            )
        }
    }

    val pagerState = rememberPagerState(
        currentPage = selectedIndex.value,
        maxPage = tabsResId.lastIndex
    )

    Pager(
        lastPage = tabsResId.lastIndex,
        state = pagerState,
        offscreenLimit = 1
    ) {
        when (currentPage) {
            RecipesTabs.SAVED.ordinal -> SavedRecipes(savedRecipes, markRecipeAsFavourite)
            RecipesTabs.FAVOURITE.ordinal -> FavouriteRecipes(
                favouriteRecipes,
                markRecipeAsFavourite
            )
        }
    }
}

private const val EMPTY_URL = ""
private val RECIPE_ITEM_SPACING = 16.dp

// TODO for BottomNavigation, check if can be remove by spacer
private val RECIPE_ITEM_SPACING_BOTTOM = 64.dp

@Composable
private fun SavedRecipes(
    savedRecipes: List<Recipe>,
    markRecipeAsFavourite: (Recipe, Boolean) -> Unit
) {
    LazyColumnForIndexed(
        items = savedRecipes,
        contentPadding = PaddingValues(
            start = RECIPE_ITEM_SPACING,
            end = RECIPE_ITEM_SPACING,
            bottom = RECIPE_ITEM_SPACING_BOTTOM,
            top = RECIPE_ITEM_SPACING
        )
    ) { _, recipe ->
        RecipeDetailedPosterCard(
            modifier = Modifier,
            isFavourite = recipe.isFavourite ?: false,
            onCheckedChange = { isChecked -> markRecipeAsFavourite(recipe, isChecked) },
            posterImage = {
                CoilImage(
                    data = recipe.imageUrl ?: EMPTY_URL,
                    fadeIn = true,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.aspectRatio(1.46f)
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
}

@Composable
private fun FavouriteRecipes(
    favouriteRecipes: List<Recipe>,
    markRecipeAsFavourite: (Recipe, Boolean) -> Unit
) {
    VerticalGrid(columns = 2) {
        favouriteRecipes.forEach { recipe ->
            RecipeDetailedPosterCard(
                isFavourite = recipe.isFavourite ?: false,
                onCheckedChange = { isChecked -> markRecipeAsFavourite(recipe, isChecked) },
                posterImage = {
                    CoilImage(
                        data = recipe.imageUrl ?: EMPTY_URL,
                        fadeIn = true,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                },
                posterDetails = {
                    RecipeDetails(
                        name = recipe.title ?: "",
                        contentTags = "2 Serving • 20 Min • 205 Cal",
                        rating = "4.6"
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalLayout::class)
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
            color = contentColorFor(color = MaterialTheme.colors.surface).copy(alpha = 0.7f)
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
            color = contentColorFor(color = MaterialTheme.colors.primarySurface)
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

private val PROFILE_LAYOUT_MARGIN = 16.dp
