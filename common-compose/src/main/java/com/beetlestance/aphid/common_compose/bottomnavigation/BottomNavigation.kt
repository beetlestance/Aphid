package com.beetlestance.aphid.common_compose.bottomnavigation

import androidx.compose.animation.AnimatedValueModel
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.VectorConverter
import androidx.compose.animation.animate
import androidx.compose.animation.asDisposableClock
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.VectorizedAnimationSpec
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonConstants
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.onCommit
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMap
import androidx.compose.ui.zIndex
import kotlin.math.sqrt

@Composable
fun CurveCutNavBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    fabBackgroundColor: Color = MaterialTheme.colors.primarySurface,
    fabContentColor: Color = contentColorFor(fabBackgroundColor),
    elevation: Dp = BottomNavigationElevation,
    selectedItem: Int = 0,
    maxItems: Int,
    fabOnClick: () -> Unit = {},
    fabIcon: @Composable CurveCutNavBarScope.() -> Unit,
    menuItems: @Composable CurveCutNavBarScope.() -> Unit
) {

    val state = remember { CurveCutNavBarState(selectedItem) }

    val scope = CurveCutNavBarScope(state)

    SubcomposeLayout<CurveCutSlots>(
        modifier = modifier
    ) { constraints ->

        val menuItemWidth = constraints.maxWidth.div(maxItems)

        val cellCentreOffsetX = menuItemWidth * state.selectedItem + menuItemWidth.div(6)

        val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)

        val navOffsetY = constraints.maxHeight - BottomNavigationHeight.toIntPx()

        val fabFabOffsetY = constraints.maxHeight - CurveCutBottomNavigationHeight.toIntPx()

        layout(constraints.maxWidth, constraints.maxHeight) {

            val bottomNavBarPlaceables = subcompose(CurveCutSlots.BOTTOM_NAV_BAR) {
                CurveCutBottomNavBar(
                    scope = scope,
                    shape = CurveCutBottomNavBarShape(
                        offsetX = animate(
                            target = cellCentreOffsetX.toDp(),
                            animSpec = CurveCutBezierEasing
                        )
                    ),
                    backgroundColor = backgroundColor,
                    contentColor = contentColor,
                    elevation = elevation,
                    menuItems = menuItems
                )
            }.fastMap {
                it.measure(looseConstraints)
            }

            val fabPlaceables = subcompose(CurveCutSlots.FAB) {
                val fabPlacement = animateBounce(
                    distance = cellCentreOffsetX.toDp(),
                    peak = FabMargin,
                    depth = CurveCutBottomNavigationHeight
                )
                CurveCutFab(
                    modifier = Modifier.offset(
                        fabPlacement.offsetX,
                        fabPlacement.offsetY
                    ),
                    scope = scope,
                    backgroundColor = fabBackgroundColor,
                    contentColor = fabContentColor,
                    fabIcon = if (fabPlacement.isDocked) fabIcon else NoIcon,
                    onClick = fabOnClick
                )
            }.fastMap { it.measure(looseConstraints) }

            // The bottom bar is always at the bottom of the layout
            bottomNavBarPlaceables.fastForEach {
                it.place(0, navOffsetY)
            }

            fabPlaceables.fastForEach {
                it.place(0, fabFabOffsetY)
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CurveCutNavBarScope.CurveCutMenuItem(
    index: Int,
    icon: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val itemModifier = if (selected) {
        state.selectedItem = index
        modifier
    } else {
        modifier.selectable(
            selected = selected,
            onClick = onClick,
            indication = null
        )
    }
    Box(
        modifier = itemModifier.size(48.dp),
        alignment = Alignment.Center,
        children = { icon() }
    )
}

@Composable
private fun CurveCutFab(
    modifier: Modifier = Modifier,
    scope: CurveCutNavBarScope,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    onClick: () -> Unit = {},
    fabIcon: @Composable CurveCutNavBarScope.() -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.size(FabRadius.times(2)),
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = FloatingActionButtonConstants.defaultElevation(
            defaultElevation = FabElevation,
            pressedElevation = FabPressedElevation
        ),
        icon = { scope.fabIcon() }
    )
}

@Composable
private fun CurveCutBottomNavBar(
    modifier: Modifier = Modifier,
    scope: CurveCutNavBarScope,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    shape: Shape,
    elevation: Dp = BottomNavigationElevation,
    menuItems: @Composable CurveCutNavBarScope.() -> Unit
) {
    Surface(
        modifier = modifier.preferredHeight(BottomNavigationHeight).zIndex(1f),
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        shape = shape
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            children = { scope.menuItems() }
        )
    }
}

private enum class CurveCutSlots { FAB, BOTTOM_NAV_BAR }

private class CurveCutBottomNavBarShape(
    val offsetX: Dp
) : Shape {

    override fun createOutline(size: Size, density: Density): Outline {
        val boundingRectangle = Path().apply {
            addRect(Rect(0f, 0f, size.width, size.height))
        }

        val path = Path().apply {
            addCutoutShape(density)
            // Subtract this path from the bounding rectangle
            op(boundingRectangle, this, PathOperation.difference)
        }

        return Outline.Generic(path)
    }

    private fun Path.addCutoutShape(density: Density) {
        // The gap on all sides between the FAB and the cutout
        val cutoutOffset = with(density) { FabMargin.toPx() }

        val cutoutSize = Size(
            width = with(density) { FabRadius.times(2).toPx() } + (cutoutOffset * 2),
            height = with(density) { FabRadius.times(2).toPx() } + (cutoutOffset * 2)
        )

        val cutoutStartX = with(density) { offsetX.toPx() } - cutoutOffset
        val cutoutEndX = cutoutStartX + cutoutSize.width

        val cutoutRadius = cutoutSize.height / 2f
        // Shift the cutout up by half its height, so only the bottom half of the cutout is actually
        // cut into the app bar
        val cutoutStartY = -cutoutRadius

        addOutline(CircleShape.createOutline(cutoutSize, density))
        translate(Offset(cutoutStartX, cutoutStartY))

        val edgeRadius = with(density) { CurveCutNavBarRoundedEdgeRadius.toPx() }
        addRoundedEdges(cutoutStartX, cutoutEndX, cutoutRadius, edgeRadius, 0f)
    }

    private fun Path.addRoundedEdges(
        cutoutStartPosition: Float,
        cutoutEndPosition: Float,
        cutoutRadius: Float,
        roundedEdgeRadius: Float,
        verticalOffset: Float
    ) {
        // Where the cutout intersects with the app bar, as if the cutout is not vertically aligned
        // with the app bar, the intersect will not be equal to the radius of the circle.
        val appBarInterceptOffset = calculateCutoutCircleYIntercept(cutoutRadius, verticalOffset)
        val appBarInterceptStartX = cutoutStartPosition + (cutoutRadius + appBarInterceptOffset)
        val appBarInterceptEndX = cutoutEndPosition - (cutoutRadius + appBarInterceptOffset)

        // How far the control point is away from the cutout intercept. We set this to be as small
        // as possible so that we have the most 'rounded' curve.
        val controlPointOffset = 1f

        // How far the control point is away from the center of the radius of the cutout
        val controlPointRadiusOffset = appBarInterceptOffset - controlPointOffset

        // The coordinates offset from the center of the radius of the cutout, where we should
        // draw the curve to
        val (curveInterceptXOffset, curveInterceptYOffset) = calculateRoundedEdgeIntercept(
            controlPointRadiusOffset,
            verticalOffset,
            cutoutRadius
        )

        // Convert the offset relative to the center of the cutout circle into an absolute
        // coordinate, by adding the radius of the shape to get a pure relative offset from the
        // leftmost edge, and then positioning it next to the cutout
        val curveInterceptStartX = cutoutStartPosition + (curveInterceptXOffset + cutoutRadius)
        val curveInterceptEndX = cutoutEndPosition - (curveInterceptXOffset + cutoutRadius)

        // Convert the curveInterceptYOffset which is relative to the center of the cutout, to an
        // absolute position
        val curveInterceptY = curveInterceptYOffset - verticalOffset

        // Where the rounded edge starts
        val roundedEdgeStartX = appBarInterceptStartX - roundedEdgeRadius
        val roundedEdgeEndX = appBarInterceptEndX + roundedEdgeRadius

        moveTo(roundedEdgeStartX, 0f)
        quadraticBezierTo(
            appBarInterceptStartX - controlPointOffset,
            0f,
            curveInterceptStartX,
            curveInterceptY
        )
        lineTo(curveInterceptEndX, curveInterceptY)
        quadraticBezierTo(appBarInterceptEndX + controlPointOffset, 0f, roundedEdgeEndX, 0f)
        close()
    }

    @Suppress("UnnecessaryVariable")
    private fun calculateRoundedEdgeIntercept(
        controlPointX: Float,
        verticalOffset: Float,
        radius: Float
    ): Pair<Float, Float> {
        val a = controlPointX
        val b = verticalOffset
        val r = radius

        // expands to a2b2r2 + b4r2 - b2r4
        val discriminant = square(b) * square(r) * (square(a) + square(b) - square(r))
        val divisor = square(a) + square(b)
        // the '-b' part of the quadratic solution
        val bCoefficient = a * square(r)

        // Two solutions for the x coordinate relative to the midpoint of the circle
        val xSolutionA = (bCoefficient - sqrt(discriminant)) / divisor
        val xSolutionB = (bCoefficient + sqrt(discriminant)) / divisor

        // Get y coordinate from r2 = x2 + y2 -> y2 = r2 - x2
        val ySolutionA = sqrt(square(r) - square(xSolutionA))
        val ySolutionB = sqrt(square(r) - square(xSolutionB))

        // If the vertical offset is 0, the vertical center of the circle lines up with the top edge of
        // the bottom app bar, so both solutions are identical.
        // If the vertical offset is not 0, there are two distinct solutions: one that will meet in the
        // top half of the circle, and one that will meet in the bottom half of the circle. As the app
        // bar is always on the bottom edge of the circle, we are always interested in the bottom half
        // solution. To calculate which is which, it depends on whether the vertical offset is positive
        // or negative.
        val (xSolution, ySolution) = if (b > 0) {
            // When the offset is positive, the top edge of the app bar is below the center of the
            // circle. The largest solution will be the one closest to the bottom of the circle, so we
            // pick that.
            if (ySolutionA > ySolutionB) xSolutionA to ySolutionA else xSolutionB to ySolutionB
        } else {
            // When the offset is negative, the top edge of the app bar is above the center of the
            // circle. The smallest solution will be the one closest to the top of the circle, so we
            // pick that.
            if (ySolutionA < ySolutionB) xSolutionA to ySolutionA else xSolutionB to ySolutionB
        }

        // If the calculated x coordinate is further away from the origin than the control point, the
        // curve will fold back on itself. In this scenario, we actually join the circle above the
        // center, so invert the y coordinate.
        val adjustedYSolution = if (xSolution < controlPointX) -ySolution else ySolution
        return xSolution to adjustedYSolution
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun square(x: Float) = x * x

    private fun calculateCutoutCircleYIntercept(
        cutoutRadius: Float,
        verticalOffset: Float
    ): Float {
        return -sqrt(square(cutoutRadius) - square(verticalOffset))
    }
}

@Stable
class CurveCutNavBarState(
    defaultSelectedItem: Int = 0
) {
    // state to remember selected item
    private var _selectedItem by mutableStateOf(defaultSelectedItem)
    var selectedItem: Int
        get() = _selectedItem
        set(value) {
            _selectedItem = value
        }
}

class CurveCutNavBarScope(
    internal val state: CurveCutNavBarState,
    val selectedId: Int = state.selectedItem
)

private fun abs(dp: Dp) = if (dp >= 0.dp) dp else -dp

private class FabPlacement(
    val isDocked: Boolean,
    val offsetX: Dp,
    val offsetY: Dp
)

@Composable
private fun animateBounce(
    distance: Dp,
    peak: Dp,
    depth: Dp
): FabPlacement {
    val clock = AnimationClockAmbient.current.asDisposableClock()

    val anim = remember(clock, Dp.VectorConverter) {
        AnimatedValueModel(distance, Dp.VectorConverter, clock)
    }

    onCommit(distance) {
        anim.animateTo(distance, CurveCutBezierEasing)
    }

    val distanceCovered = anim.value
    val offsetY: Dp = if (distanceCovered == distance) peak else depth
    return FabPlacement(
        isDocked = anim.isRunning,
        offsetX = distanceCovered,
        offsetY = animate(offsetY, CurveCutBezierEasing)
    )
}

/**
 * [VectorizedAnimationSpec] controlling the transition between unselected and selected
 * [BottomNavigationItem]s.
 *
 * This is like SlowOutSlowIn easing
 */
private val BottomNavigationAnimationSpec = TweenSpec<Float>(
    durationMillis = 300,
    easing = CubicBezierEasing(0.2f, 0f, 0.8f, 1f)
)

private val CurveCutBezierEasing = TweenSpec<Dp>(
    durationMillis = 300,
    easing = FastOutSlowInEasing
)

private val BottomNavigationHeight = 56.dp

internal val FabRadius = 56.dp.div(2)

internal val FabMargin = 8.dp

private val FabDepthMargin = 8.dp

internal val CurveCutNavBarRoundedEdgeRadius = 4.dp

private val CurveCutBottomNavigationHeight = BottomNavigationHeight + FabRadius + FabDepthMargin

internal val CurvedBottomNavigationOffset = 12.dp

private val BottomNavigationElevation = 8.dp

private val FabElevation = 12.dp

private val FabPressedElevation = 6.dp

internal val NoIcon: @Composable CurveCutNavBarScope.() -> Unit = {}

