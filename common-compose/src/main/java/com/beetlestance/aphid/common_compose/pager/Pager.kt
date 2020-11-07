package com.beetlestance.aphid.common_compose.pager

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
import androidx.compose.ui.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.unit.Density
import androidx.compose.ui.zIndex
import kotlin.math.roundToInt

/**
 * Stole from jetpack compose samples - JetCaster
 */
open class PagerState(
    val clock: AnimationClockObservable,
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

    inline fun <R> selectPage(block: PagerState.() -> R): R = try {
        selectionState = SelectionState.Undecided
        block()
    } finally {
        selectPage(currentPageOffset.roundToInt())
    }

    open fun selectPage(pageOffset: Int) {
        currentPage -= pageOffset
        currentPageOffset = 0f
        selectionState = SelectionState.Selected
    }

    protected var _currentPageOffset: AnimatedFloatModel =
        AnimatedFloatModel(0f, clock = clock).apply {
            setBounds(-1f, 1f)
        }

    open fun offset(page: Int): Float = page - (currentPage - currentPageOffset)

    var currentPageOffset: Float
        get() = _currentPageOffset.value
        set(value) {
            snapToValue(value)
        }

    protected open fun snapToValue(value: Float) {
        val max = if (currentPage == minPage) 0f else 1f
        val min = if (currentPage == maxPage) 0f else -1f
        // sets offset to provided value immediately without any animation
        _currentPageOffset.snapTo(value.coerceIn(min, max))
    }


    fun nextPage(velocity: Float = 5f) {
        fling(-velocity)
    }

    fun previousPage(velocity: Float = 5f) {
        fling(velocity)
    }

    open fun fling(velocity: Float) {
        selectionState = SelectionState.Undecided
        if (velocity < 0 && currentPage == maxPage) return
        if (velocity > 0 && currentPage == minPage) return

        _currentPageOffset.fling(velocity) { reason, _, _ ->
            if (reason != AnimationEndReason.Interrupted) {
                _currentPageOffset.animateTo(currentPageOffset.roundToInt().toFloat()) { _, _ ->
                    selectPage(currentPageOffset.roundToInt())
                }
            }
        }
    }

    override fun toString(): String = "PagerState{minPage=$minPage, maxPage=$maxPage, " +
            "currentPage=$currentPage, currentPageOffset=$currentPageOffset}"
}

@Immutable
internal data class PageData(val page: Int) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any? = this@PageData
}

private val Measurable.page: Int
    get() = (parentData as? PageData)?.page ?: error("no PageData for measurable $this")

@Composable
fun rememberPagerState(
    clock: AnimationClockObservable = AnimationClockAmbient.current,
    currentPage: Int = 0,
    minPage: Int = 0,
    maxPage: Int = 0
): PagerState {
    return remember(clock) {
        PagerState(
            clock = clock,
            currentPage = currentPage,
            minPage = minPage,
            maxPage = maxPage
        )
    }
}


@Composable
fun Pager(
    state: PagerState,
    offscreenLimit: Int = 2,
    modifier: Modifier = Modifier,
    pageContent: @Composable PagerScope.() -> Unit
) {
    val minPage = (state.currentPage - offscreenLimit).coerceAtLeast(state.minPage)
    val maxPage = (state.currentPage + offscreenLimit).coerceAtMost(state.maxPage)

    PageLayout(
        state = state,
        offscreenLimit = offscreenLimit,
        modifier = modifier,
        children = {
            for (page in minPage..maxPage) {
                val pageData = PageData(page)
                val scope = PagerScope(state, page)
                key(pageData) {
                    Box(
                        alignment = Alignment.Center,
                        modifier = pageData
                            // Always draw selected page after its next hint
                            .zIndex(animate(if (page == state.currentPage) 1f else 0f))
                    ) {
                        scope.pageContent()
                    }
                }
            }
        },
        isCarousel = false,
        minPage = minPage,
        maxPage = maxPage
    )
}

@Composable
fun PageLayout(
    state: PagerState,
    offscreenLimit: Int = 2,
    modifier: Modifier = Modifier,
    minPage: Int,
    maxPage: Int,
    isCarousel: Boolean,
    children: @Composable () -> Unit
) {
    var pageSize by remember { mutableStateOf(0) }

    Layout(
        children = children,
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
                val maxDragSize = pageSize * offscreenLimit
                val minDragSize = -pageSize * offscreenLimit
                val max = if (currentPage == minPage && isCarousel.not()) 0 else maxDragSize
                val min = if (currentPage == maxPage && isCarousel.not()) 0 else minDragSize
                val newPos = (pos + dy).coerceIn(min.toFloat(), max.toFloat())
                currentPageOffset = newPos / pageSize
            }
        }
    ) { measurables, constraints ->
        layout(constraints.maxWidth, constraints.maxHeight) {
            val currentPage = state.currentPage
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

                    placeable.place(
                        x = xCenterOffset + (state.offset(page) * placeable.width).roundToInt(),
                        y = yCenterOffset
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
    private val page: Int
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


    fun nextPage(velocity: Float): Unit = state.nextPage(velocity)

    fun previousPage(velocity: Float): Unit = state.previousPage(velocity)

    /**
     * Modifier which scales pager items according to their offset position. Similar in effect
     * to a carousel.
     */
    fun Modifier.transformPage(
        pageTransition: PageTransformation = PageTransformation.NONE
    ): Modifier = drawWithContent {
        val transform = pageTransition.transformPage(state.offset(page), size)
        this.withTransform(transformBlock = {
            this.scale(transform.scaleX, transform.scaleY, Offset(center.x, center.y))
            this.translate(transform.translationX, transform.translationY)
        }) {
            this@drawWithContent.drawContent()
        }
    }
}

