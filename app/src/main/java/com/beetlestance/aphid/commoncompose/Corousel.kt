package com.beetlestance.aphid.commoncompose

import androidx.compose.animation.animate
import androidx.compose.animation.core.AnimationClockObservable
import androidx.compose.animation.core.AnimationEndReason
import androidx.compose.animation.core.fling
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Layout
import androidx.compose.ui.Measurable
import androidx.compose.ui.Modifier
import androidx.compose.ui.ParentDataModifier
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.unit.Density
import androidx.compose.ui.zIndex
import kotlin.math.roundToInt


class CarouselState(
    clock: AnimationClockObservable,
    currentPage: Int = 0,
    minPage: Int = 0,
    maxPage: Int = 0
) : PagerState(clock, currentPage, minPage, maxPage) {

    override fun selectPage(pageOffset: Int) {
        val page = currentPage - pageOffset
        currentPage = if (page > maxPage) minPage else if (page < minPage) maxPage else page
        currentPageOffset = 0f
        selectionState = SelectionState.Selected
    }

    override fun snapToValue(value: Float) {
        _currentPageOffset.snapTo(value)
    }

    override fun fling(velocity: Float) {
        _currentPageOffset.fling(velocity) { reason, _, _ ->
            if (reason != AnimationEndReason.Interrupted) {
                _currentPageOffset.animateTo(currentPageOffset.roundToInt().toFloat()) { _, _ ->
                    selectPage(currentPageOffset.roundToInt())
                }
            }
        }
    }
}

@Composable
fun rememberCarouselState(
    clock: AnimationClockObservable = AnimationClockAmbient.current,
    currentPage: Int = 0,
    minPage: Int = 0,
    maxPage: Int = 0
): CarouselState {
    return remember(clock) {
        CarouselState(
            clock = clock,
            currentPage = currentPage,
            minPage = minPage,
            maxPage = maxPage
        )
    }
}

@Composable
fun <T> Carousel(
    items: List<T>,
    offscreenLimit: Int = 2,
    state: CarouselState = rememberCarouselState(maxPage = items.lastIndex),
    modifier: Modifier = Modifier,
    pageContent: @Composable (CarouselScope.(T) -> Unit)
) {
    if (items.size < 3) throw IllegalArgumentException("Carousel needs at least 3 items")
    var pageSize by remember { mutableStateOf(0) }

    Layout(
        children = {
            val minPageInfinite = state.currentPage - offscreenLimit.coerceAtMost(items.size / 2)
            val maxPageInfinite = state.currentPage + offscreenLimit.coerceAtMost(items.size / 2)

            for (page in minPageInfinite..maxPageInfinite) {
                val index = when {
                    page < state.minPage -> items.size + page
                    page > state.maxPage -> page - state.maxPage - 1
                    else -> page
                }

                val pageData = CarouselPageData(index)
                val scope = CarouselScope(state, index)
                key(pageData) {
                    Box(
                        alignment = Alignment.Center,
                        modifier = pageData
                            // Always draw selected page after its next hint
                            .zIndex(animate(if (page == state.currentPage) 1f else 0f))
                    ) {
                        scope.pageContent(items[index])
                    }
                }
            }
        },
        modifier = modifier.draggable(
            orientation = Orientation.Horizontal,
            onDragStarted = {
                state.selectionState = PagerState.SelectionState.Undecided
            },
            onDragStopped = { velocity ->
                // Velocity is in pixels per second, but we deal in percentage offsets, so we
                // need to scale the velocity to match
                state.fling(velocity / pageSize)
            }
        ) { dy ->
            with(state) {
                val pos = pageSize * currentPageOffset
                val max = pageSize * offscreenLimit
                val min = -pageSize * offscreenLimit
                val newPos = (pos + dy).coerceIn(min.toFloat(), max.toFloat())
                currentPageOffset = newPos / pageSize
            }
        }
    ) { measurables, constraints ->
        layout(constraints.maxWidth, constraints.maxHeight) {
            val currentPage = state.currentPage
            val currentPageOffset = state.currentPageOffset
            val childConstraints = constraints.copy(minWidth = 0, minHeight = 0)

            measurables
                .map {
                    it.measure(childConstraints) to it.page
                }
                .forEach { (placeable, page) ->
                    // TODO: current this centers each page. We should investigate reading
                    //  gravity modifiers on the child, or maybe as a param to Pager.
                    val xCenterOffset = (constraints.maxWidth - placeable.width) / 2
                    val yCenterOffset = (constraints.maxHeight - placeable.height) / 2

                    if (currentPage == page) {
                        pageSize = placeable.width
                    }

                    val offset = pageOffsetWithCurrent(
                        currentPage,
                        page,
                        state.maxPage,
                        state.minPage,
                        currentPageOffset
                    )

                    val xItemOffset = (offset * placeable.width).roundToInt()

                    placeable.place(
                        x = xCenterOffset + xItemOffset,
                        y = yCenterOffset
                    )
                }
        }
    }
}

@Immutable
private data class CarouselPageData(val page: Int) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any? = this@CarouselPageData
}

private val Measurable.page: Int
    get() = (parentData as? CarouselPageData)?.page ?: error("no PageData for measurable $this")

class CarouselScope(
    private val carouselState: CarouselState,
    page: Int
) : PagerScope(carouselState, page) {

    override fun offset(): Float = pageOffsetWithCurrent(
        currentPage,
        page,
        carouselState.maxPage,
        carouselState.minPage,
        currentPageOffset
    )
}
