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
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ConfigurationAmbient
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.beetlestance.aphid.common_compose.FoodCardPage
import com.beetlestance.aphid.common_compose.extensions.widthPercentage
import com.beetlestance.aphid.common_compose.pager.Carousel
import com.beetlestance.aphid.common_compose.pager.PageConfig
import com.beetlestance.aphid.common_compose.pager.PageTransformation
import com.beetlestance.aphid.common_compose.pager.Pager
import com.beetlestance.aphid.common_compose.utils.navViewModel
import com.beetlestance.aphid.data.entities.Recipe
import com.beetlestance.spoonacular_kotlin.SpoonacularImageHelper
import com.beetlestance.spoonacular_kotlin.constants.SpoonacularImageSize
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun Explore(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues()
) {
    val viewModel: ExploreViewModel by navViewModel()
    val state by viewModel.liveData.observeAsState(initial = viewModel.currentState())
    val actions: (ExploreActions) -> Unit = { action -> viewModel.submitAction(action) }

    val contentPadding = PaddingValues(
        top = if (paddingValues.top > 0.dp) paddingValues.top else EXPLORE_ITEM_SPACING,
        start = if (paddingValues.start > 0.dp) paddingValues.start else EXPLORE_ITEM_SPACING,
        end = if (paddingValues.end > 0.dp) paddingValues.end else EXPLORE_ITEM_SPACING,
        bottom = if (paddingValues.bottom > 0.dp) paddingValues.bottom else EXPLORE_ITEM_SPACING
    )

    Explore(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding(),
        state = state,
        paddingValues = contentPadding,
        actions = actions
    )
}

private val EXPLORE_ITEM_SPACING = 16.dp

@OptIn(ExperimentalFocus::class)
@Composable
private fun Explore(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    state: ExploreViewState,
    actions: (ExploreActions) -> Unit
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.surface
    ) {
        ScrollableColumn(
            modifier = Modifier.fillMaxSize().animateContentSize(),
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(
                space = 24.dp,
                alignment = Alignment.Top
            )
        ) {
            Spacer(modifier = Modifier.preferredHeight(0.dp))

            SearchBar(modifier = Modifier.padding(horizontal = 8.dp))

            if (state.breakfastRecipes.isNotEmpty()) {
                BreakFastWithHeader(
                    breakfastRecipes = state.breakfastRecipes,
                    markRecipeAsFavourite = { recipe, isFavourite ->
                        actions(MarkFavourite(recipe, isFavourite))
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
                        actions(MarkFavourite(recipe, isFavourite))
                    }
                )
            }

            if (state.recentlyViewedRecipes.isNotEmpty()) {
                RecentlyViewedRecipesWithHeader(
                    recentlyViewedRecipes = state.recentlyViewedRecipes,
                    markRecipeAsFavourite = { recipe, isFavourite ->
                        actions(MarkFavourite(recipe, isFavourite))
                    }
                )
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
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .drawShadow(
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
                asset = Icons.Outlined.Search,
                tint = Color.Gray,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .align(Alignment.CenterVertically)
            )

            Text(
                text = if (text.isEmpty()) "Search for food" else text,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                color = Color.Gray
            )
        }

        Icon(
            asset = vectorResource(id = R.drawable.ic_filter),
            tint = Color.Gray,
            modifier = Modifier
                .clip(CircleShape)
                .clickable(
                    onClick = filterClick
                )
                .padding(8.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun BreakFastWithHeader(
    breakfastRecipes: List<Recipe>,
    markRecipeAsFavourite: (Recipe, Boolean) -> Unit
) {

    Text(
        text = stringResource(id = R.string.explore_breaksfast_header),
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    val items = if (breakfastRecipes.any { it.isFavourite == true }) {
        breakfastRecipes.take(4)
    } else {
        breakfastRecipes
    }

    Carousel(
        items = items,
        offscreenLimit = 2,
        pageHint = 24.dp,
        drawSelectedPageAtLast = true
    ) { recipe ->
        val recipeImageUrl: String? = run {
            return@run SpoonacularImageHelper.generateRecipeImageUrl(
                id = recipe.recipeId?.toLong() ?: return@run null,
                imageSize = SpoonacularImageSize.Recipe.ULTRA_HIGH_QUALITY,
                imageType = recipe.imageType
            )
        }

        ExploreBreakfastCard(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(13 / 20f)
                .transformPage(PageTransformation.SCALE_TRANSFORM),
            imageSrc = recipeImageUrl ?: recipe.imageUrl ?: "",
            isFavourite = recipe.isFavourite ?: false,
            title = recipe.title ?: "",
            subTitle = "2 Serving • 40 Min • 331 Cal",
            onMarkFavourite = {
                markRecipeAsFavourite(recipe, it)
            },
            description =
            "A unique experience of taste  and delicious ingredients prepared for you. Liven up your life with nutrition."
        )
    }
}

@Composable
fun MoodContent() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        val (text, image) = createRefs()

        val endGuideline = createGuidelineFromEnd(fraction = 0.05f)

        val topGuideline = createGuidelineFromTop(fraction = 0.15f)

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

        Image(
            modifier = Modifier.constrainAs(image) {
                linkTo(start = parent.start, end = parent.end, bias = 1f)
                width = Dimension.percent(0.2f)
            }.aspectRatio(1f),
            asset = vectorResource(id = R.drawable.ic_cookie)
        )
    }

}

@Composable
fun Cuisine() {

    Text(
        text = stringResource(id = R.string.explore_cuisine_header),
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    ScrollableRow {
        repeat(8) {
            CuisineContent()
        }
    }
}

@Composable
fun CuisineContent() {
    val itemWidth = (ConfigurationAmbient.current.screenWidthDp * 0.3f).dp
    Column(
        modifier = Modifier
            .preferredWidth(itemWidth)
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
        border = BorderStroke(2.dp, colorResource(id = R.color.purple_600))
    ) {
        Image(
            modifier = Modifier.fillMaxWidth().clipToBounds().aspectRatio(1f),
            asset = imageResource(id = R.drawable.temp_brownie),
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
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
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

@Composable
fun QuickRecipesWithHeader(
    quickRecipes: List<Recipe>,
    markRecipeAsFavourite: (Recipe, Boolean) -> Unit
) {
    Text(
        text = stringResource(id = R.string.explore_quick_recipes_header),
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    val pageConfig = PageConfig(
        horizontalOffset = 16.dp,
        fraction = 0.9f,
        horizontalOffsetFraction = 0.1f,
        aspectRatio = 3 / 2f,
        maxWidth = widthPercentage(fraction = 0.9f, excludeRootPadding = 16.dp)
    )

    Pager(
        lastPage = quickRecipes.lastIndex
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
            placeholder = R.drawable.temp_brownie,
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
        modifier = Modifier.padding(horizontal = 16.dp)
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
            placeholder = R.drawable.temp_brownie,
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
