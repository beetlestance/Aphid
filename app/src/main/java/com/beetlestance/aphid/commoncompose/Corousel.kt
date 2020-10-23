package com.beetlestance.aphid.commoncompose

import androidx.compose.animation.animate
import androidx.compose.animation.core.AnimationClockObservable
import androidx.compose.animation.core.AnimationEndReason
import androidx.compose.animation.core.fling
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Measurable
import androidx.compose.ui.Modifier
import androidx.compose.ui.ParentDataModifier
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

    override fun offset(page: Int): Float = pageOffsetWithCurrent(
        currentPage,
        page,
        maxPage,
        minPage,
        currentPageOffset
    )
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

    val minPage = state.currentPage - offscreenLimit.coerceAtMost(maximumValue = items.size / 2)
    val maxPage = state.currentPage + offscreenLimit.coerceAtMost(maximumValue = items.size / 2)

    PageLayout(
        state = state,
        offscreenLimit = offscreenLimit,
        modifier = modifier,
        child = { page ->
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
        },
        isCarousel = false,
        minPage = minPage,
        maxPage = maxPage
    )
}

@Immutable
private data class CarouselPageData(val page: Int) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any? = this@CarouselPageData
}

private val Measurable.page: Int
    get() = (parentData as? CarouselPageData)?.page ?: error("no PageData for measurable $this")

class CarouselScope(
    carouselState: CarouselState,
    page: Int
) : PagerScope(carouselState, page)

internal fun pageOffsetWithCurrent(
    currentPage: Int,
    page: Int,
    maxPage: Int,
    minPage: Int,
    currentPageOffset: Float
): Float {
    val offsetFromEnd = maxPage - currentPage
    val offsetFromStart = currentPage - minPage
    val isEndOfList = offsetFromEnd < offsetFromStart
    val isStartingList = offsetFromStart < offsetFromEnd

    return when {
        isEndOfList && page == minPage -> {
            offsetFromEnd + page + 1 + currentPageOffset
        }

        isStartingList && page == maxPage -> {
            -1 - currentPage + currentPageOffset
        }

        else -> page - currentPage + currentPageOffset
    }
}
