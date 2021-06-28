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
package com.beetlestance.aphid.common_compose.pager

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.beetlestance.aphid.common_compose.extensions.toIntPx
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.roundToInt
import kotlin.math.sign

/**
 * Stole from jetpack compose samples
 * @see <a href="https://github.com/android/compose-samples/blob/main/Jetcaster/app/src/main/java/com/example/jetcaster/util/Pager.kt">
 *     JetCaster Pager
 *     </a>
 */
open class PagerState(
    currentPage: Int = 0,
    minPage: Int = 0,
    maxPage: Int = 0
) {
    private var _minPage by mutableStateOf(minPage)
    var minPage: Int
        get() = _minPage
        set(value) {
            _minPage = value.coerceAtMost(_maxPage)
            _currentPage = _currentPage.coerceIn(_minPage, _maxPage)
        }

    private var _maxPage by mutableStateOf(maxPage, structuralEqualityPolicy())
    var maxPage: Int
        get() = _maxPage
        set(value) {
            _maxPage = value.coerceAtLeast(_minPage)
            _currentPage = _currentPage.coerceIn(_minPage, maxPage)
        }

    private var _currentPage by mutableStateOf(currentPage.coerceIn(minPage, maxPage))
    var currentPage: Int
        get() = _currentPage
        set(value) {
            _currentPage = value.coerceIn(minPage, maxPage)
        }

    enum class SelectionState { Selected, Undecided }

    var selectionState: SelectionState by mutableStateOf(SelectionState.Selected)

    suspend inline fun <R> selectPage(block: PagerState.() -> R): R = try {
        selectionState = SelectionState.Undecided
        block()
    } finally {
        selectPage()
    }

    open suspend fun selectPage() {
        currentPage -= currentPageOffset.roundToInt()
        snapToValue(0f)
        selectionState = SelectionState.Selected
    }

    protected var _currentPageOffset = Animatable(0f).apply {
        updateBounds(-1f, 1f)
    }

    open fun offset(page: Int): Float = page - (currentPage - currentPageOffset)

    val currentPageOffset: Float
        get() = _currentPageOffset.value

    open suspend fun snapToValue(value: Float) {
        val max = if (currentPage == minPage) 0f else 1f
        val min = if (currentPage == maxPage) 0f else -1f
        // sets offset to provided value immediately without any animation
        _currentPageOffset.snapTo(value.coerceIn(min, max))
    }

    suspend fun nextPage(velocity: Float = 5f) {
        fling(-velocity)
    }

    suspend fun previousPage(velocity: Float = 5f) {
        fling(velocity)
    }

    open suspend fun fling(velocity: Float) {
        selectionState = SelectionState.Undecided
        if (velocity < 0 && currentPage == maxPage) return
        if (velocity > 0 && currentPage == minPage) return

        _currentPageOffset.animateTo(currentPageOffset.roundToInt().toFloat())
        selectPage()
    }

    fun updateState(
        newMaxPage: Int
    ) {
        maxPage = newMaxPage
        if (newMaxPage < currentPage) {
            currentPage = newMaxPage
        }
    }

    override fun toString(): String = "PagerState{minPage=$minPage, maxPage=$maxPage, " +
        "currentPage=$currentPage, currentPageOffset=$currentPageOffset}"
}

@Immutable
internal data class PageData(val page: Int) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any = this@PageData
}

private val Measurable.page: Int
    get() = (parentData as? PageData)?.page ?: error("no PageData for measurable $this")

@Composable
fun rememberPagerState(
    currentPage: Int = 0,
    minPage: Int = 0,
    maxPage: Int = 0
): PagerState {
    val state = remember {
        PagerState(
            currentPage = currentPage,
            minPage = minPage,
            maxPage = maxPage
        )
    }

    state.updateState(newMaxPage = maxPage)

    return state
}

@Composable
fun Pager(
    modifier: Modifier = Modifier,
    lastPage: Int,
    offscreenLimit: Int = 2,
    state: PagerState = rememberPagerState(maxPage = lastPage),
    drawSelectedPageAtLast: Boolean = false, // for overlap-transformations
    content: @Composable PagerScope.() -> Unit
) {
    val minPage = (state.currentPage - offscreenLimit).coerceAtLeast(state.minPage)
    val maxPage = (state.currentPage + offscreenLimit).coerceAtMost(state.maxPage)

    PageLayout(
        state = state,
        offscreenLimit = offscreenLimit,
        modifier = modifier,
        content = {
            for (page in minPage..maxPage) {
                val pageData = PageData(page)
                val scope = PagerScope(state, rememberCoroutineScope(), page)
                key(pageData) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = if (drawSelectedPageAtLast) pageData
                            // Always draw selected page after its next hint
                            .zIndex(if (page == state.currentPage) 1f else 0f) else pageData
                    ) {
                        scope.content()
                    }
                }
            }
        },
        isCarousel = false,
        minPage = minPage,
        maxPage = maxPage
    )
}

private val PageContentVerticalPadding = 32.dp
val NO_HINT: Dp = 0.dp

@Composable
fun PageLayout(
    modifier: Modifier = Modifier,
    state: PagerState,
    offscreenLimit: Int = 2,
    minPage: Int,
    maxPage: Int,
    isCarousel: Boolean,
    pageHint: Dp = NO_HINT,
    content: @Composable () -> Unit
) {
    var pageSize by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current

    Layout(
        content = content,
        modifier = modifier
            .animateContentSize()
            .draggable(
                orientation = Orientation.Horizontal,
                onDragStarted = {
                    state.selectionState = PagerState.SelectionState.Undecided
                },
                onDragStopped = { velocity ->
                    coroutineScope.launch {
                        // Velocity is in pixels per second, but we deal in percentage offsets, so we
                        // need to scale the velocity to match
                        state.fling(velocity / pageSize)
                    }
                },
                state = rememberDraggableState { dy ->
                    coroutineScope.launch {
                        with(state) {
                            val pos = pageSize * currentPageOffset
                            val maxDragSize = pageSize * offscreenLimit
                            val minDragSize = -pageSize * offscreenLimit
                            val max =
                                if (currentPage == minPage && isCarousel.not()) 0 else maxDragSize
                            val min =
                                if (currentPage == maxPage && isCarousel.not()) 0 else minDragSize
                            val newPos = (pos + dy).coerceIn(min.toFloat(), max.toFloat())
                            snapToValue(newPos / pageSize)
                        }
                    }
                }
            )
    ) { measurables, constraints ->

        var layoutHeight = constraints.maxHeight
        val layoutWidth = constraints.maxWidth

        // current selected page
        val currentPage = state.currentPage

        // child layout placeables
        val placeables = arrayOfNulls<Placeable>(measurables.size)
        val pages = Array(measurables.size) { measurables[it].page }
        measurables.forEachIndexed { index, measurable ->
            val placeable = measurable.measure(
                constraints = constraints.copy(
                    minWidth = 0,
                    minHeight = 0
                )
            )

            // As current page is the page without any transformation we need the width for
            // measuring drag size
            if (currentPage == pages[index]) {
                pageSize = placeable.width
            }

            layoutHeight = placeable.height + PageContentVerticalPadding.times(2).toIntPx(density)
            placeables[index] = placeable
        }

        layout(layoutWidth, layoutHeight) {
            placeables.forEachIndexed { index, placeable ->
                placeable ?: return@layout
                val page = pages[index]

                val itemScrollXOffset = state.offset(page)

                val horizontalSpacingForChildren = layoutWidth - placeable.width
                val verticalSpacingForChildren = layoutHeight - placeable.height

                val hint =
                    sign(itemScrollXOffset).toInt() * (pageHint.toIntPx(density) * itemScrollXOffset.absoluteValue).toInt()
                        .coerceAtMost(horizontalSpacingForChildren.div(2))

                val xCenterOffset =
                    horizontalSpacingForChildren.div(2) + (placeable.width * itemScrollXOffset).roundToInt()

                placeable.place(
                    x = xCenterOffset - hint,
                    y = verticalSpacingForChildren.div(2)
                )
            }
        }
    }
}

/**
 * Scope for [Pager] content.
 */
open class PagerScope(
    private val state: PagerState,
    private val coroutineScope: CoroutineScope,
    val page: Int
) {
    /**
     * Returns the current selected page
     */
    val currentPage: Int
        get() = state.currentPage

    /**
     * Returns the current selected page offset
     */
    val currentPageOffset: Float
        get() = state.currentPageOffset

    /**
     * Returns the current selection state
     */
    val selectionState: PagerState.SelectionState
        get() = state.selectionState

    val isSelectedPage: Boolean
        get() = currentPage == page

    fun nextPage(velocity: Float) {
        coroutineScope.launch { state.nextPage(velocity) }
    }

    fun previousPage(velocity: Float) {
        coroutineScope.launch {
            state.previousPage(velocity)
        }
    }

    /**
     * Modifier which scales pager items according to their offset position. Similar in effect
     * to a carousel.
     */
    fun Modifier.transformPage(
        pageTransition: PageTransformation = PageTransformation.NONE
    ): Modifier = drawWithContent {
        val transform = pageTransition.transformPage(state.offset(page), size)
        this.withTransform(
            transformBlock = {
                this.scale(transform.scaleX, transform.scaleY, Offset(center.x, center.y))
                this.translate(transform.translationX, transform.translationY)
            }
        ) {
            this@drawWithContent.drawContent()
        }
    }
}
