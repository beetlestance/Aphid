package com.beetlestance.aphid

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Icon
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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.platform.ConfigurationAmbient
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.beetlestance.aphid.commoncompose.Carousel
import com.beetlestance.aphid.commoncompose.ViewPagerTransition
import com.beetlestance.data.entities.Recipe
import com.beetlestance.spoonacular_kotlin.SpoonacularImageHelper
import com.beetlestance.spoonacular_kotlin.constants.SpoonacularImageSize
import timber.log.Timber

/**
 * If possible manage all the states in the top level composable for the screen
 */
@OptIn(ExperimentalFocus::class)
@Composable
fun Explore(state: ExploreViewState) {
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
                    markRecipeAsFavourite = state.markRecipeAsFavourite
                )
            }

            MoodContent()

            Cuisine()

            PlanYourMealAheadWithHeader()

            QuickRecipesWithHeader()

            RecentlyViewedRecipesWithHeader()
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

    val offset = 16.dp
    val fraction = 0.9f
    val foodCard = FoodCard(
        horizontalOffset = offset,
        fraction = fraction,
        horizontalOffsetFraction = 0.1f,
        maxWidth = widthPercentage(fraction = fraction, excludeRootPadding = offset)
    )

    Carousel(
        items = breakfastRecipes,
        offscreenLimit = 2,
        modifier = Modifier.preferredHeight(foodCard.maxHeight)
    ) { recipe ->
        val recipeImageUrl: String? = run {
            return@run SpoonacularImageHelper.generateRecipeImageUrl(
                id = recipe.recipeId?.toLong() ?: return@run null,
                imageSize = SpoonacularImageSize.Recipe.ULTRA_HIGH_QUALITY,
                imageType = recipe.imageType
            )
        }

        FoodCardWithDetailsPage(
            modifier = Modifier.transitionPageItem(ViewPagerTransition.ZOOM_OUT),
            foodCard = foodCard,
            isSelected = isSelectedPage,
            name = recipe.title ?: "",
            imageUrl = recipeImageUrl ?: recipe.imageUrl,
            imageResource = R.drawable.temp_brownie,
            contentTags = "2 Serving • 40 Min • 331 Cal ",
            rating = "4.3",
            isFavourite = recipe.isFavourite == true,
            onCheckedChange = { isFavourite -> markRecipeAsFavourite(recipe, isFavourite) }
        )
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
fun QuickRecipesWithHeader() {

    Text(
        text = stringResource(id = R.string.explore_quick_recipes_header),
        style = MaterialTheme.typography.h6,
    )

    ScrollableRow(
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.Start
        )
    ) {

        repeat(4) {
            FoodCardWithDetails(
                imageResource = R.drawable.temp_noodles,
                contentTags = "1 Serving • 20 Min • 205 Cal",
                rating = "4.4",
                name = "Asian Pesto Noodles",
                fraction = 0.7f,
                horizontalOffset = 16.dp,
                isFavourite = false
            )
        }
    }
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
fun RecentlyViewedRecipesWithHeader() {

    Text(
        text = stringResource(id = R.string.explore_quick_recipes_header),
        style = MaterialTheme.typography.h6,
    )

    ScrollableRow(
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.Start
        )
    ) {

        repeat(4) {
            FoodCardWithDetails(
                imageResource = R.drawable.temp_pasta,
                contentTags = "1 Serving • 25 Min • 190 Cal",
                rating = "4.4",
                name = "One Pot Pasta",
                fraction = 0.7f,
                horizontalOffset = 16.dp,
                isFavourite = false
            )
        }
    }
}
