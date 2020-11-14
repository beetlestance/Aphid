package com.beetlestance.aphid.feature_explore

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.Dimension
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.foundation.layout.FlowCrossAxisAlignment
import androidx.compose.foundation.layout.FlowMainAxisAlignment
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.SizeMode
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.savedinstancestate.savedInstanceState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.isFocused
import androidx.compose.ui.focusObserver
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.platform.ConfigurationAmbient
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.beetlestance.aphid.common_compose.FoodCardPage
import com.beetlestance.aphid.common_compose.extensions.widthPercentage
import com.beetlestance.aphid.common_compose.pager.Carousel
import com.beetlestance.aphid.common_compose.pager.CarouselState
import com.beetlestance.aphid.common_compose.pager.PageConfig
import com.beetlestance.aphid.common_compose.pager.PageTransformation
import com.beetlestance.aphid.common_compose.pager.Pager
import com.beetlestance.aphid.common_compose.pager.rememberPagerState
import com.beetlestance.aphid.data.entities.Recipe
import com.beetlestance.spoonacular_kotlin.SpoonacularImageHelper
import com.beetlestance.spoonacular_kotlin.constants.SpoonacularImageSize
import timber.log.Timber

/**
 * If possible manage all the states in the top level composable for the screen
 */
@OptIn(ExperimentalFocus::class)
@Composable
fun Explore(
    state: ExploreViewState,
    action: (ExploreActions) -> Unit
) {
    Surface(color = MaterialTheme.colors.surface) {
        ScrollableColumn(
            modifier = Modifier.fillMaxSize().animateContentSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(
                space = 24.dp,
                alignment = Alignment.Top
            )
        ) {
            Timber.d("$state")
            val searchState = rememberSearchState()
            val searchQuery = savedInstanceState(saver = TextFieldValue.Saver) {
                TextFieldValue(searchState.query)
            }
            Search(
                value = searchQuery.value,
                hint = searchState.hint,
                onValueChange = {
                    searchQuery.value = it
                    searchState.query = it.text
                },
                onFocusChange = { searchState.focused = it.isFocused }
            )

            Filters()

            if (state.breakfastRecipes.isNotEmpty()) {
                BreakFastWithHeader(
                    breakfastRecipes = state.breakfastRecipes,
                    markRecipeAsFavourite = { recipe, isFavourite ->
                        action(MarkFavourite(recipe, isFavourite))
                    }
                )
            }

            MoodContent()

            Cuisine()

            PlanYourMealAheadWithHeader()

            if (state.readyInTimeRecipes.isNotEmpty()) {
                QuickRecipesWithHeader(
                    quickRecipes = state.readyInTimeRecipes,
                    markRecipeAsFavourite = { recipe, isFavourite ->
                        action(MarkFavourite(recipe, isFavourite))
                    }
                )
            }

            if (state.recentlyViewedRecipes.isNotEmpty()) {
                RecentlyViewedRecipesWithHeader(
                    recentlyViewedRecipes = state.recentlyViewedRecipes,
                    markRecipeAsFavourite = { recipe, isFavourite ->
                        action(MarkFavourite(recipe, isFavourite))
                    }
                )
            }

            // for bottomNavigation
            Spacer(modifier = Modifier.preferredHeight(64.dp))
        }
    }
}

/**
 * This component should be stateless
 */
@OptIn(ExperimentalFocus::class)
@Composable
fun Search(
    value: TextFieldValue,
    hint: String,
    onValueChange: (TextFieldValue) -> Unit,
    onFocusChange: (FocusState) -> Unit
) {
    val placeHolder = "Palak Paneer"
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth().focusObserver { onFocusChange(it) },
        leadingIcon = { Icon(Icons.Outlined.Search) },
        label = { SearchHint(hint = hint) },
        placeholder = { SearchPlaceHolder(placeHolder = placeHolder) }
    )
}

@Composable
fun SearchHint(hint: String) {
    Text(text = hint)
}

@Composable
fun SearchPlaceHolder(placeHolder: String) {
    Text(text = placeHolder)
}

@Composable
private fun rememberSearchState(
    query: String = "",
    focused: Boolean = false
): SearchState {
    return remember {
        SearchState(
            query = query,
            focused = focused
        )
    }
}

@Stable
class SearchState(
    query: String,
    focused: Boolean
) {
    var query: String by mutableStateOf(query)
    var focused: Boolean by mutableStateOf(focused)

    val hint: String
        get() = if (focused) "Search For Food" else "Palak Paneer"
}

@Composable
fun Filters(filters: List<Filter> = provideFilters()) {
    ScrollableRow(
        horizontalArrangement = Arrangement.spacedBy(
            space = 8.dp,
            alignment = Alignment.End
        )
    ) {
        filters.forEach { filter ->
            FilterChip(filter = filter)
        }
    }
}

@Composable
fun FilterChip(filter: Filter) {
    val backGround = if (filter.selected) R.color.deep_orange_a200 else R.color.white

    Row(
        horizontalArrangement = Arrangement.spacedBy(
            space = 8.dp,
            alignment = Alignment.Start
        ),
        modifier = Modifier
            .animateContentSize()
            .border(
                width = if (filter.selected) 0.dp else 2.dp,
                color = colorResource(id = R.color.grey_700),
                shape = CircleShape
            )
            .clip(CircleShape)
            .background(
                color = colorResource(id = backGround)
            )
            .toggleable(
                value = filter.selected,
                onValueChange = { filter.selected = it }
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
    ) {
        Text(text = "Juices")

        if (filter.selected) {
            Icon(
                asset = vectorResource(id = R.drawable.ic_close_filled),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun provideFilters(): List<Filter> {
    val filters: MutableList<Filter> = mutableListOf()
    repeat(10) {
        filters.add(Filter(selected = false))
    }
    return remember { filters }
}

class Filter(
    selected: Boolean
) {
    var selected: Boolean by mutableStateOf(selected)
}


@Composable
fun BreakFastWithHeader(
    breakfastRecipes: List<Recipe>,
    markRecipeAsFavourite: (Recipe, Boolean) -> Unit
) {

    Text(
        text = stringResource(id = R.string.explore_breaksfast_header),
        style = MaterialTheme.typography.h6,
    )

    val foodCardPageConfig = PageConfig(
        horizontalOffset = 16.dp,
        fraction = 0.9f,
        horizontalOffsetFraction = 0.1f,
        aspectRatio = 13f / 20f,
        maxWidth = widthPercentage(fraction = 0.9f, excludeRootPadding = 16.dp)
    )

    val items = if (breakfastRecipes.any { it.isFavourite == true }) {
        breakfastRecipes.take(4)
    } else {
        breakfastRecipes
    }

    Carousel(
        items = items,
        offscreenLimit = 2,
        modifier = Modifier.preferredHeight(foodCardPageConfig.maxHeight),
        drawSelectedPageAtLast = true
    ) { recipe ->
        val recipeImageUrl: String? = run {
            return@run SpoonacularImageHelper.generateRecipeImageUrl(
                id = recipe.recipeId?.toLong() ?: return@run null,
                imageSize = SpoonacularImageSize.Recipe.ULTRA_HIGH_QUALITY,
                imageType = recipe.imageType
            )
        }

        FoodCardPage(
            modifier = Modifier.transformPage(PageTransformation.DEPTH_TRANSFORM),
            pageConfig = foodCardPageConfig,
            isSelected = isSelectedPage,
            imageUrl = recipeImageUrl ?: recipe.imageUrl,
            placeholder = R.drawable.temp_brownie,
            isFavourite = recipe.isFavourite == true,
            onCheckedChange = { isFavourite -> markRecipeAsFavourite(recipe, isFavourite) },
            childPreferredHeight = FOOD_CARD_DETAILS_HEIGHT
        ) {
            FoodCardContentsDetails(
                name = recipe.title ?: "",
                contentTags = "2 Serving • 40 Min • 331 Cal ",
                rating = "4.3",
            )
        }
    }
}

@Composable
fun MoodContent() {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

        val (text, image) = createRefs()

        val endGuideline = createGuidelineFromEnd(fraction = 0.05f)

        val topGuideline = createGuidelineFromTop(fraction = 0.15f)

        Image(
            modifier = Modifier.constrainAs(image) {
                linkTo(start = parent.start, end = parent.end, bias = 1f)
                width = Dimension.percent(0.2f)
            }.aspectRatio(1f),
            asset = vectorResource(id = R.drawable.ic_cookie)
        )

        Text(
            modifier = Modifier
                .constrainAs(text) {
                    linkTo(start = parent.start, end = endGuideline)
                    linkTo(top = topGuideline, bottom = parent.bottom)
                    width = Dimension.fillToConstraints
                }
                .background(
                    color = colorResource(id = R.color.amber_500),
                    shape = RoundedCornerShape(16.dp)
                ).padding(16.dp),
            text = "What Are You In Mood For Today ?",
            style = MaterialTheme.typography.h5
        )
    }

}

@Composable
fun Cuisine() {

    Text(
        text = stringResource(id = R.string.explore_cuisine_header),
        style = MaterialTheme.typography.h6,
    )

    ScrollableRow(
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.Start
        )
    ) {

        repeat(8) {
            CuisineContent()
        }
    }
}

@Composable
fun CuisineContent() {
    val itemWidth = (ConfigurationAmbient.current.screenWidthDp * 0.3f).dp
    Column(
        modifier = Modifier.preferredWidth(itemWidth).fillMaxWidth(),
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
        border = BorderStroke(2.dp, colorResource(id = R.color.purple_600))
    ) {
        Image(
            modifier = Modifier.fillMaxWidth().clipToBounds().aspectRatio(1f),
            asset = imageResource(id = R.drawable.temp_chinese),
            contentScale = ContentScale.Crop
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
fun PlanYourMealAheadWithHeader() {

    Text(
        text = stringResource(id = R.string.explore_plan_your_meal_ahead),
        style = MaterialTheme.typography.h6,
    )

    ScrollableRow(
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        // per item width in row
        val itemWidth = (ConfigurationAmbient.current.screenWidthDp * 1f).dp - 32.dp

        Card(
            modifier = Modifier.preferredWidth(itemWidth).fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = 4.dp,
            backgroundColor = colorResource(id = R.color.gun_powder)
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
                    modifier = Modifier.fillMaxWidth()
                        .clipToBounds()
                        .align(Alignment.Bottom)
                        .weight(0.6f)
                        .padding(top = 16.dp),
                    asset = vectorResource(id = R.drawable.ic_plan_your_meal),
                    contentScale = ContentScale.Crop
                )
            }
        }

    }
}

@Composable
fun QuickRecipesWithHeader(
    quickRecipes: List<Recipe>,
    markRecipeAsFavourite: (Recipe, Boolean) -> Unit
) {
    Text(
        text = stringResource(id = R.string.explore_quick_recipes_header),
        style = MaterialTheme.typography.h6,
    )

    val pageConfig = PageConfig(
        horizontalOffset = 16.dp,
        fraction = 0.9f,
        horizontalOffsetFraction = 0.1f,
        aspectRatio = 3 / 2f,
        maxWidth = widthPercentage(fraction = 0.9f, excludeRootPadding = 16.dp)
    )

    Pager(
        lastPage = quickRecipes.lastIndex,
        modifier = Modifier.preferredHeight(pageConfig.maxHeight)
    ) {
        val recipe = quickRecipes.getOrNull(page) ?: return@Pager
        val recipeImageUrl: String? = run {
            return@run SpoonacularImageHelper.generateRecipeImageUrl(
                id = recipe.recipeId?.toLong() ?: return@run null,
                imageSize = SpoonacularImageSize.Recipe.ULTRA_HIGH_QUALITY,
                imageType = recipe.imageType
            )
        }

        FoodCardPage(
            modifier = Modifier.transformPage(PageTransformation.ZOOM_OUT),
            pageConfig = pageConfig,
            placeholder = R.drawable.temp_noodles,
            isSelected = page == currentPage,
            imageUrl = recipeImageUrl ?: "",
            onCheckedChange = { isFavourite -> markRecipeAsFavourite(recipe, isFavourite) },
            isFavourite = recipe.isFavourite == true,
            childPreferredHeight = FOOD_CARD_DETAILS_HEIGHT
        ) {
            FoodCardContentsDetails(
                name = recipe.title ?: "",
                contentTags = "1 Serving • 20 Min • 205 Cal",
                rating = "4.4"
            )
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RecentlyViewedRecipesWithHeader(
    recentlyViewedRecipes: List<Recipe>,
    markRecipeAsFavourite: (Recipe, Boolean) -> Unit
) {

    Text(
        text = stringResource(id = R.string.explore_recently_viewed_recipes_header),
        style = MaterialTheme.typography.h6,
    )

    val pageConfig = PageConfig(
        horizontalOffset = 16.dp,
        fraction = 0.9f,
        horizontalOffsetFraction = 0.1f,
        aspectRatio = 3 / 2f,
        maxWidth = widthPercentage(fraction = 0.9f, excludeRootPadding = 16.dp)
    )

    Pager(
        lastPage = recentlyViewedRecipes.lastIndex,
        modifier = Modifier.preferredHeight(pageConfig.maxHeight)
    ) {
        val recipe = recentlyViewedRecipes[page]
        val recipeImageUrl: String? = run {
            return@run SpoonacularImageHelper.generateRecipeImageUrl(
                id = recipe.recipeId?.toLong() ?: return@run null,
                imageSize = SpoonacularImageSize.Recipe.ULTRA_HIGH_QUALITY,
                imageType = recipe.imageType
            )
        }

        FoodCardPage(
            modifier = Modifier.transformPage(PageTransformation.STAIRCASE_TRANSFORM),
            pageConfig = pageConfig,
            placeholder = R.drawable.temp_noodles,
            isSelected = page == currentPage,
            imageUrl = recipeImageUrl ?: "",
            onCheckedChange = { isFavourite -> markRecipeAsFavourite(recipe, isFavourite) },
            isFavourite = recipe.isFavourite == true,
            childPreferredHeight = FOOD_CARD_DETAILS_HEIGHT
        ) {
            AnimatedVisibility(
                visible = page == currentPage,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut(),
            ) {
                Column {
                    FoodCardContentsDetails(
                        name = recipe.title ?: "",
                        contentTags = "1 Serving • 20 Min • 205 Cal",
                        rating = "4.4"
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayout::class)
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
            color = colorResource(id = com.beetlestance.aphid.common_compose.R.color.grey_700)
        )

        Text(
            modifier = Modifier
                .background(
                    color = colorResource(id = com.beetlestance.aphid.common_compose.R.color.deep_orange_a200),
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

private val FOOD_CARD_DETAILS_HEIGHT = 80.dp
