package com.beetlestance.aphid.commoncompose

import android.util.Log
import androidx.compose.animation.AnimatedFloatModel
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
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Layout
import androidx.compose.ui.Measurable
import androidx.compose.ui.Modifier
import androidx.compose.ui.ParentDataModifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.unit.Density
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import com.beetlestance.aphid.SearchState
import java.lang.Math.abs
import kotlin.math.roundToInt

/**
 * Stole from jetpack compose samples - JetCaster
 */

@Composable
fun rememberPagerState(
    clock: AnimationClockObservable = AnimationClockAmbient.current,
    currentPage: Int = 0,
    minPage: Int = 0,
    maxPage: Int = 0,
    isInfiniteScroll: Boolean = true
): PagerState {
    return remember(clock) {
        PagerState(
            clock = clock,
            currentPage = currentPage,
            minPage = minPage,
            maxPage = maxPage,
            isInfiniteScroll = isInfiniteScroll
        )
    }
}


data class PageTransformState(
    val alpha: Float = 1f,
    val scaleX: Float = 1f,
    val scaleY: Float = 1f,
    val translationX: Float = 0f,
    val translationY: Float = 0f
)

class PagerState(
    val clock: AnimationClockObservable,
    currentPage: Int = 0,
    minPage: Int = 0,
    maxPage: Int = 0,
    val isInfiniteScroll: Boolean = false
) {
    private val pagesToShow = if (isInfiniteScroll) maxPage + 3 else maxPage

    private var _minPage by mutableStateOf(minPage)
    var minPage: Int
        get() = _minPage
        set(value) {
            _minPage = value.coerceAtMost(_maxPage)
            _currentPage = _currentPage.coerceIn(_minPage, _maxPage)
        }

    private var _maxPage by mutableStateOf(pagesToShow, structuralEqualityPolicy())
    var maxPage: Int
        get() = _maxPage
        set(value) {
            _maxPage = value.coerceAtLeast(_minPage)
            _currentPage = _currentPage.coerceIn(_minPage, maxPage)
        }

    private var _currentPage by mutableStateOf(currentPage.coerceIn(minPage, pagesToShow))
    var currentPage: Int
        get() = _currentPage
        set(value) {
            _currentPage = value.coerceIn(minPage, maxPage)
        }

    enum class SelectionState { Selected, Undecided }

    var selectionState by mutableStateOf(SelectionState.Selected)

    inline fun <R> selectPage(block: PagerState.() -> R): R = try {
        selectionState = SelectionState.Undecided
        block()
    } finally {
        selectPage(currentPageOffset.roundToInt())
    }

    fun selectPage(pageOffset: Int) {
        currentPage -= pageOffset
        currentPageOffset = 0f
        selectionState = SelectionState.Selected
    }

    private var _currentPageOffset = AnimatedFloatModel(0f, clock = clock).apply {
        setBounds(-1f, 1f)
    }

    var currentPageOffset: Float
        get() = _currentPageOffset.value
        set(value) {
            val max = if (currentPage == minPage) 0f else 1f
            val min = if (currentPage == maxPage) 0f else -1f
            // sets offset to provided value immediately without any animation
            _currentPageOffset.snapTo(value.coerceIn(min, max))
        }

    fun resetIfScrollEnd() {
        if (isInfiniteScroll) {
            if (currentPage == maxPage - 1) currentPage = 1
        } else {
            currentPage = 0
        }
    }

    fun nextPage(velocity: Float = 5f) {
        if (isInfiniteScroll) resetIfScrollEnd()
        fling(-velocity)
    }

    fun previousPage(velocity: Float = 5f) {
        fling(velocity)
    }

    fun fling(velocity: Float) {
        if (velocity < 0 && currentPage == maxPage && isInfiniteScroll.not()) return
        if (velocity > 0 && currentPage == minPage) return

        selectionState = SelectionState.Undecided

        _currentPageOffset.fling(velocity) { reason, _, _ ->
            if (reason != AnimationEndReason.Interrupted) {
                _currentPageOffset.animateTo(currentPageOffset.roundToInt().toFloat()) { _, _ ->
                    selectPage(currentPageOffset.roundToInt())
                }
            }
        }
    }

    val numberOfPages: Int
        get() = if (isInfiniteScroll) maxPage - 3 else maxPage


    override fun toString(): String = "PagerState{minPage=$minPage, maxPage=$maxPage, " +
            "currentPage=$currentPage, currentPageOffset=$currentPageOffset}"
}

@Immutable
private data class PageData(val page: Int) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any? = this@PageData
}

private val Measurable.page: Int
    get() = (parentData as? PageData)?.page ?: error("no PageData for measurable $this")

fun getActualPosition(page: Int, listSize: Int, isRepeat: Boolean): Int {
    return if (isRepeat) page - listSize - 1 else page
}

@Composable
fun Pager(
    state: PagerState,
    offscreenLimit: Int = 2,
    modifier: Modifier = Modifier,
    pageContent: @Composable PagerScope.() -> Unit
) {
    var pageSize by remember { mutableStateOf(0) }

    Layout(
        children = {
            val minPage = (state.currentPage - offscreenLimit).coerceAtLeast(state.minPage)
            val maxPage = (state.currentPage + offscreenLimit).coerceAtMost(state.maxPage)

            for (page in minPage..maxPage) {
                val isRepeat = page > state.numberOfPages
                val pageInList = if (isRepeat) page - state.numberOfPages - 1 else page
                val pageData = PageData(pageInList)
                val scope = PagerScope(state, pageInList, isRepeat)
                key(pageData) {
                    Box(
                        alignment = Alignment.Center,
                        modifier = pageData
                        // Always draw selected page after its next hint
                        //.zIndex(animate(if (scope.isSelectedPage) 1f else 0f))
                    ) {
                        scope.pageContent()
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
                val max = if (currentPage == minPage) 0 else pageSize * offscreenLimit
                val min = if (currentPage == maxPage) 0 else -pageSize * offscreenLimit
                val newPos = (pos + dy).coerceIn(min.toFloat(), max.toFloat())
                currentPageOffset = newPos / pageSize
            }
        }
    ) { measurables, constraints ->
        layout(constraints.maxWidth, constraints.maxHeight) {
            val currentPage = state.currentPage
            val offset = state.currentPageOffset
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

                    // add current page offset to calculate child offset correctly
                    val actualPage = if (currentPage >= state.numberOfPages && page in 0..2) {
                        page + state.numberOfPages + 1
                    } else {
                        page
                    }

                    val xItemOffset =
                        ((actualPage + offset - currentPage) * placeable.width).roundToInt()

                    placeable.place(
                        x = xCenterOffset + xItemOffset,
                        y = yCenterOffset
                    )
                }
        }
    }
}

/**
 * Scope for [Pager] content.
 */
@Suppress("UNUSED_PARAMETER")
class PagerScope(
    private val state: PagerState,
    val page: Int,
    val isRepeatPage: Boolean
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
        get() {
            if (state.isInfiniteScroll.not())
                return currentPage == page

            val current = if (currentPage > state.numberOfPages)
                currentPage - state.numberOfPages - 1
            else
                currentPage

            return current == page
        }

    fun nextPage(velocity: Float, resetIfSrollEnd: Boolean) {
        state.nextPage()
    }

    fun previousPage(velocity: Float) = state.previousPage(velocity)

    /**
     * Modifier which scales pager items according to their offset position. Similar in effect
     * to a carousel.
     */
    fun Modifier.transitionPageItem(
        pageTransition: ViewPagerTransition = ViewPagerTransition.NONE
    ): Modifier = drawWithContent {
        val pagerPage = if (isRepeatPage) page + state.numberOfPages + 1 else page
        val offsetForPage = pagerPage - currentPage + currentPageOffset
        val transform = pageTransition.transformPage(offsetForPage, size)
        this.withTransform(transformBlock = {
            this.scale(transform.scaleX, transform.scaleY, Offset(center.x, center.y))
            this.translate(transform.translationX, transform.translationY)
        }) {
            this@drawWithContent.drawContent()
        }
        /*if (selectionState == PagerState.SelectionState.Selected) {
            // If the pager is 'selected', it's stationary so we use a simple if check
            if (isSelectedPage.not()) {
                this.withTransform(transformBlock = {
                    if (overflow) {
                        this.translate(
                            top = 0f,
                            left = if (pagerPage > currentPage) -200f else 200f
                        )
                    }
                    this.scale(scaleX = 0.8f, scaleY = 0.8f, pivot = Offset(center.x, center.y))
                }) {
                    this@drawWithContent.drawContent()
                }
            } else {
                drawContent()
            }
        } else {
            // Otherwise the pager is being scrolled, so we need to look at the swipe progress
            // and interpolate between the sizes
            val offsetForPage = pagerPage - currentPage + currentPageOffset

            val scale = if (offsetForPage < 0) {
                // If the page is to the left of the current page, we scale from min -> 1f
                lerp(
                    start = 0.8f,
                    stop = 1f,
                    fraction = (1f + offsetForPage).coerceIn(0f, 1f)
                )
            } else {
                // If the page is to the right of the current page, we scale from 1f -> min
                lerp(
                    start = 1f,
                    stop = 0.8f,
                    fraction = offsetForPage.coerceIn(0f, 1f)
                )
            }

            val translateX = if (offsetForPage < 0) {
                // If the page is to the left of the current page, we scale from min -> 1f
                lerp(
                    start = 200f,
                    stop = 0f,
                    fraction = (1f + offsetForPage).coerceIn(0f, 1f)
                )
            } else {
                // If the page is to the right of the current page, we scale from 1f -> min
                lerp(
                    start = 0f,
                    stop = -200f,
                    fraction = offsetForPage.coerceIn(0f, 1f)
                )
            }

            this.withTransform(transformBlock = {
                if (overflow) {
                    this.translate(top = 0f, left = translateX)
                }
                this.scale(scaleX = scale, scaleY = scale, pivot = Offset(center.x, center.y))
            }) {
                this@drawWithContent.drawContent()
            }
        }*/
    }
}

private const val MIN_SCALE = 0.75f

private const val MIN_SCALE_ZOOM = 0.9f
private const val MIN_ALPHA = 0.7f


/**
 * Offset meaning:
 * less than 0 : specifies that the page is in left side of the current page
 * equals to 0 : specifies that it is current page
 * greater than 0 : specifies that page is on the right side of the current page
 */
interface ViewPagerTransition {
    fun transformPage(offset: Float, size: Size): PageTransformState

    companion object {
        val NONE = object : ViewPagerTransition {
            override fun transformPage(
                offset: Float,
                size: Size
            ): PageTransformState {
                return PageTransformState()
            }
        }

        val DEPTH_TRANSFORM = object : ViewPagerTransition {
            override fun transformPage(
                offset: Float,
                size: Size
            ): PageTransformState {
                return when {
                    offset == 0f -> PageTransformState()
                    offset < 0f -> {
                        val scaleFactor = (MIN_SCALE + (1 - MIN_SCALE) * (1 - kotlin.math.abs(
                            offset
                        )))
                        PageTransformState(
                            1 - offset, scaleFactor, scaleFactor,
                            size.width * -offset
                        )
                    }
                    else -> {
                        PageTransformState()
                    }
                }
            }
        }

        val ZOOM_OUT = object : ViewPagerTransition {
            override fun transformPage(
                offset: Float,
                size: Size
            ): PageTransformState {
                return when {
                    offset <= 1 && offset >= -1 -> {
                        val scaleFactor = MIN_SCALE_ZOOM.coerceAtLeast(1 - abs(offset))
                        val vertMargin = size.height * (1 - scaleFactor) / 2
                        val horzMargin = size.width * (1 - scaleFactor) / 2
                        val translationX = if (offset < 0) {
                            0f
                        } else {
                            // if page is on the right side do not apply any translation
                            //horzMargin + vertMargin / 2
                            0f
                        }

                        val alpha = (MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE_ZOOM) / (1 - MIN_SCALE_ZOOM)) * (1 - MIN_ALPHA)))
                        PageTransformState(
                            alpha,
                            scaleFactor,
                            scaleFactor,
                            translationX
                        )
                    }
                    else -> PageTransformState(0f, 0f, 0f)
                }
            }
        }
    }
}
