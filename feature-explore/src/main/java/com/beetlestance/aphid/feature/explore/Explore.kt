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
package com.beetlestance.aphid.feature.explore

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.beetlestance.aphid.common.compose.RecipeDetailedPosterCard
import com.beetlestance.aphid.common.compose.theme.Amber500
import com.beetlestance.aphid.common.compose.theme.DeepOrange200
import com.beetlestance.aphid.common.compose.theme.Grey700
import com.beetlestance.aphid.common.compose.theme.GunPowder
import com.beetlestance.aphid.common.compose.theme.Purple200
import com.beetlestance.aphid.data.entities.Recipe
import com.beetlestance.aphid.feature.explore.ExploreItems.BREAKFAST
import com.beetlestance.aphid.feature.explore.ExploreItems.CUISINE
import com.beetlestance.aphid.feature.explore.ExploreItems.MOOD
import com.beetlestance.aphid.feature.explore.ExploreItems.PLAN_YOUR_MEAL
import com.beetlestance.aphid.feature.explore.ExploreItems.QUICK_RECIPES
import com.beetlestance.aphid.feature.explore.ExploreItems.RECENTLY_VIEWED
import com.beetlestance.aphid.feature.explore.ExploreItems.SEARCH_BAR
import com.beetlestance.aphid.spoonacular.kotlin.SpoonacularImageHelper
import com.beetlestance.aphid.spoonacular.kotlin.constants.SpoonacularImageSize
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun Explore(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues()
) {
    val viewModel: ExploreViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Explore(
        modifier = modifier.fillMaxSize(),
        contentPadding = paddingValues,
        state = state,
        onMarkRecipeAsFavourite = { recipe, isFavourite ->
            viewModel.markRecipeAsFavourite(recipe, isFavourite)
        }
    )
}

private val EXPLORE_ITEM_SPACING = 16.dp

private val EXPLORE_ITEMS = listOf(
    SEARCH_BAR,
    BREAKFAST,
    MOOD,
    CUISINE,
    PLAN_YOUR_MEAL,
    QUICK_RECIPES,
    RECENTLY_VIEWED
)

private enum class ExploreItems {
    SEARCH_BAR,
    BREAKFAST,
    MOOD,
    CUISINE,
    PLAN_YOUR_MEAL,
    QUICK_RECIPES,
    RECENTLY_VIEWED
}

@Composable
private fun Explore(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    state: ExploreViewState,
    onMarkRecipeAsFavourite: (recipe: Recipe, isFavourite: Boolean) -> Unit
) {
    Scaffold(modifier = modifier) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .animateContentSize(),
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(
                space = EXPLORE_ITEM_SPACING,
                alignment = Alignment.Top
            )
        ) {
            // Using index as key, because items are pre-filled immutable list
            itemsIndexed(
                items = EXPLORE_ITEMS, key = { index, _ -> index }
            ) { _, item ->
                when (item) {
                    SEARCH_BAR -> SearchBar()
                    BREAKFAST -> {
                        if (state.breakfastRecipes.isNotEmpty()) {
                            ExploreSectionHeader(R.string.explore_breaksfast_header)
                            BreakFastRecipies(
                                breakfastRecipes = state.breakfastRecipes,
                                markRecipeAsFavourite = onMarkRecipeAsFavourite
                            )
                        }
                    }
                    MOOD -> MoodContent()
                    CUISINE -> {
                        ExploreSectionHeader(R.string.explore_cuisine_header)
                        Cuisine()
                    }
                    PLAN_YOUR_MEAL -> {
                        ExploreSectionHeader(R.string.explore_plan_your_meal_ahead)
                        PlanYourMealAhead()
                    }
                    QUICK_RECIPES -> {
                        if (state.readyInTimeRecipes.isNotEmpty()) {
                            ExploreSectionHeader(R.string.explore_quick_recipes_header)
                            QuickRecipes(
                                quickRecipes = state.readyInTimeRecipes,
                                markRecipeAsFavourite = onMarkRecipeAsFavourite
                            )
                        }
                    }
                    RECENTLY_VIEWED -> {
                        if (state.recentlyViewedRecipes.isNotEmpty()) {
                            ExploreSectionHeader(R.string.explore_recently_viewed_recipes_header)
                            RecentlyViewedRecipes(
                                recentlyViewedRecipes = state.recentlyViewedRecipes,
                                markRecipeAsFavourite = onMarkRecipeAsFavourite
                            )
                        }
                    }
                }
            }
        }
    }
}

private val ExposeSearchBarShape = CircleShape

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String = "",
    searchClick: () -> Unit = {},
    filterClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = ExposeSearchBarShape,
                    clip = false
                )
                .clip(ExposeSearchBarShape)
                .clickable(onClick = searchClick)
                .background(Color.White)
                .padding(8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .align(Alignment.CenterVertically),
                imageVector = Icons.Outlined.Search,
                tint = Color.Gray,
                contentDescription = "Search Recipes"
            )

            Text(
                text = text.ifEmpty { "Search for food" },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                color = Color.Gray
            )
        }

        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .clickable(
                    onClick = filterClick
                )
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_filter),
            tint = Color.Gray,
            contentDescription = "Search Recipes"
        )
    }
}

@Composable
fun ExploreSectionHeader(resId: Int) {
    Text(
        text = stringResource(id = resId),
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(16.dp)
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BreakFastRecipies(
    breakfastRecipes: List<Recipe>,
    markRecipeAsFavourite: (Recipe, Boolean) -> Unit
) {
    val pageCount = breakfastRecipes.size
    val startIndex = Int.MAX_VALUE / 2
    val pagerState = rememberPagerState(initialPage = startIndex)

    val recipeFor: (Int) -> Recipe = { index ->
        val pageIndex = (index - startIndex).mod(pageCount)
        breakfastRecipes[pageIndex]
    }

    HorizontalPager(
        count = Int.MAX_VALUE,
        state = pagerState,
        itemSpacing = 4.dp,
        key = { index ->
            val recipe = recipeFor(index)
            return@HorizontalPager "${recipe.recipeId}_$index"
        },
        contentPadding = PaddingValues(horizontal = 48.dp, vertical = 16.dp)
    ) { index ->
        val recipe = recipeFor(index)
        val recipeImageUrl: String = run {
            return@run SpoonacularImageHelper.generateRecipeImageUrl(
                id = recipe.recipeId.toLong(),
                imageSize = SpoonacularImageSize.Recipe.ULTRA_HIGH_QUALITY,
                imageType = recipe.imageType
            )
        }

        ExploreBreakfastCard(
            modifier = Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(index).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                },
            imageSrc = recipeImageUrl,
            isFavourite = recipe.isFavourite ?: false,
            title = recipe.title ?: "",
            subTitle = "2 Serving • 40 Min • 331 Cal",
            description =
            "A unique experience of taste  and delicious ingredients prepared for you. Liven up your life with nutrition."
        ) { isLiked -> markRecipeAsFavourite(recipe, isLiked) }
    }
}

@Composable
fun MoodContent() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        val (box, text, image) = createRefs()

        val endGuideline = createGuidelineFromEnd(fraction = 0.05f)

        val topGuideline = createGuidelineFromTop(fraction = 0.15f)

        Box(
            modifier = Modifier
                .constrainAs(box) {
                    linkTo(start = parent.start, end = endGuideline)
                    linkTo(top = topGuideline, bottom = parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
                .background(
                    color = Amber500,
                    shape = RoundedCornerShape(16.dp)
                )
        )

        Text(
            modifier = Modifier
                .constrainAs(text) {
                    linkTo(start = parent.start, end = image.start)
                    linkTo(top = topGuideline, bottom = parent.bottom)
                    width = Dimension.fillToConstraints
                }
                .padding(16.dp),
            text = "What Are You In Mood For Today ?",
            style = MaterialTheme.typography.h5
        )

        Image(
            modifier = Modifier
                .constrainAs(image) {
                    linkTo(start = parent.start, end = parent.end, bias = 1f)
                    width = Dimension.percent(0.2f)
                }
                .zIndex(1f)
                .aspectRatio(1f),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_cookie),
            contentDescription = "Cookies"
        )
    }
}

@Composable
fun Cuisine() {
    LazyRow {
        items(8) {
            CuisineContent()
        }
    }
}

@Composable
fun CuisineContent() {
    val itemWidth = (LocalConfiguration.current.screenWidthDp * 0.3f).dp
    Column(
        modifier = Modifier
            .width(itemWidth)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 4.dp,
            alignment = Alignment.Top
        )
    ) {
        CuisineCard()

        CuisineDetails()
    }
}

@Composable
fun CuisineCard() {
    Card(
        shape = CircleShape,
        border = BorderStroke(2.dp, Purple200)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .clipToBounds()
                .aspectRatio(1f),
            bitmap = ImageBitmap.imageResource(id = R.drawable.temp_brownie),
            contentScale = ContentScale.Crop,
            contentDescription = "Available Cuisines"
        )
    }
}

@Composable
fun CuisineDetails() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Creamy Donut",
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Center
    )
}

@Composable
fun PlanYourMealAhead() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        backgroundColor = GunPowder
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
                    .weight(0.4f)
                    .wrapContentSize(),
                text = "Plan Your Lunch",
                color = Color.White,
                style = MaterialTheme.typography.h5
            )

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .clipToBounds()
                    .align(Alignment.Bottom)
                    .weight(0.6f)
                    .padding(top = 16.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_plan_your_meal),
                contentScale = ContentScale.Crop,
                contentDescription = "Meal Planner"
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun QuickRecipes(
    quickRecipes: List<Recipe>,
    markRecipeAsFavourite: (Recipe, Boolean) -> Unit
) {
    HorizontalPager(
        count = quickRecipes.size,
        state = rememberPagerState(initialPage = quickRecipes.size / 2),
        key = { index -> quickRecipes[index].recipeId },
        contentPadding = when {
            quickRecipes.size > 1 -> PaddingValues(horizontal = 32.dp)
            else -> PaddingValues(0.dp)
        }
    ) { index ->
        val recipe = quickRecipes.getOrNull(index) ?: return@HorizontalPager
        RecipeDetailedPosterCard(
            modifier = Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(index).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                },
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
                    contentDescription = "Quick Recipe"
                )
            },
            posterDetails = {
                FoodCardContentsDetails(
                    name = recipe.title ?: "",
                    contentTags = "1 Serving • 20 Min • 205 Cal",
                    rating = "4.4"
                )
            }
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RecentlyViewedRecipes(
    recentlyViewedRecipes: List<Recipe>,
    markRecipeAsFavourite: (Recipe, Boolean) -> Unit
) {
    HorizontalPager(
        count = recentlyViewedRecipes.size,
        state = rememberPagerState(initialPage = recentlyViewedRecipes.size / 2),
        key = { index -> recentlyViewedRecipes[index].recipeId },
        contentPadding = PaddingValues(
            horizontal = animateDpAsState(
                targetValue = if (recentlyViewedRecipes.size > 1) 32.dp else 0.dp
            ).value
        )
    ) { index ->
        val recipe = recentlyViewedRecipes.getOrNull(index) ?: return@HorizontalPager
        RecipeDetailedPosterCard(
            modifier = Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(index).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                },
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
                    contentDescription = "Recently Viewed Recipe"
                )
            },
            posterDetails = {
                Column {
                    FoodCardContentsDetails(
                        name = recipe.title ?: "",
                        contentTags = "1 Serving • 20 Min • 205 Cal",
                        rating = "4.4"
                    )
                }
            }
        )
    }
}

@Composable
fun FoodCardContentsDetails(
    contentTags: String,
    rating: String,
    name: String
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
            color = Grey700
        )

        Text(
            modifier = Modifier
                .background(
                    color = DeepOrange200,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 12.dp, vertical = 2.dp),
            text = rating,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.surface
        )
    }

    Text(
        text = name,
        style = MaterialTheme.typography.body1,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

private const val EMPTY_URL = ""
