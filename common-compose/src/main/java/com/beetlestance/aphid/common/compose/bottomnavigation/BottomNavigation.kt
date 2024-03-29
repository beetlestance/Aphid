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
package com.beetlestance.aphid.common.compose.bottomnavigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.zIndex
import com.beetlestance.aphid.common.compose.extensions.toIntPx
import com.beetlestance.aphid.common.compose.extensions.toPx
import com.beetlestance.aphid.common.compose.theme.shapes.CutOutShape

/**
 *  Curve Cut Bottom navigation.
 *  @see <a href="https://in.pinterest.com/pin/648729521319201054/">Inspired By</a>
 *  @see <a href="https://medium.com/swlh/curved-cut-out-bottom-navigation-with-animation-in-android-c630c867958c">Cut out shape reference</a>
 */
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
    val state = remember { CurveCutNavBarState(selectedItem, maxItems) }
    val density = LocalDensity.current

    val scope = CurveCutNavBarScope(state)

    SubcomposeLayout(
        modifier = modifier.height(CurveCutBottomNavigationHeight)
    ) { constraints ->

        val menuItemWidth = constraints.maxWidth.div(maxItems)

        val menuItemStartOffsetX = menuItemWidth * state.selectedItem

        val menuItemCenterOffsetX = menuItemStartOffsetX + menuItemWidth.div(2)

        val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)

        val navOffsetY = constraints.maxHeight - BottomNavigationHeight.toIntPx(density)

        val fabFabOffsetY = constraints.maxHeight - CurveCutBottomNavigationHeight.toIntPx(density)

        val fabOffsetX = menuItemCenterOffsetX - FabRadius.toIntPx(density)

        layout(constraints.maxWidth, constraints.maxHeight) {
            val bottomNavBarPlaceables = subcompose(CurveCutSlots.BOTTOM_NAV_BAR) {
                CurveCutBottomNavBar(
                    scope = scope,
                    shape = curveCutShape(offsetX = fabOffsetX.toDp()),
                    backgroundColor = backgroundColor,
                    contentColor = contentColor,
                    elevation = elevation,
                    content = menuItems
                )
            }.map { it.measure(looseConstraints) }

            val fabPlaceables = subcompose(CurveCutSlots.FAB) {
                val fabPlacement = animateEmerge(
                    startOffset = fabOffsetX.toDp(),
                    emergedDepth = FabDepth,
                    subMergedDepth = CurveCutBottomNavigationHeight
                )
                CurveCutFab(
                    modifier = Modifier.offset(
                        fabPlacement.offsetX,
                        fabPlacement.offsetY
                    ),
                    scope = scope,
                    backgroundColor = fabBackgroundColor,
                    contentColor = fabContentColor,
                    content = {
                        AnimatedVisibility(
                            visible = fabPlacement.isDocked,
                            enter = fadeIn(),
                            exit = fadeOut(),
                            content = { if (fabPlacement.isDocked) fabIcon() else NoIcon() }
                        )
                    },
                    onClick = fabOnClick
                )
            }.map { it.measure(looseConstraints) }

            // The bottom bar is always at the bottom of the layout
            bottomNavBarPlaceables.forEach {
                it.place(0, navOffsetY)
            }

            fabPlaceables.forEach {
                it.place(0, fabFabOffsetY)
            }
        }
    }
}

@Composable
fun CurveCutNavBarScope.CurveCutMenuItem(
    modifier: Modifier = Modifier,
    index: Int,
    selected: Boolean,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val itemModifier = if (selected) {
        state.selectedItem = index
        modifier
    } else {
        modifier.selectable(
            selected = false,
            onClick = onClick,
            interactionSource = MutableInteractionSource(),
            indication = null
        )
    }

    Box(
        modifier = itemModifier
            .size(48.dp)
            .graphicsLayer {
                alpha = lerp(
                    start = if (selected) 1f else 0f,
                    stop = if (selected) 0f else 1f,
                    fraction = (state.maxItems - index).toFloat()
                )
            },
        contentAlignment = Alignment.Center,
        content = { content() }
    )
}

@Composable
private fun CurveCutFab(
    modifier: Modifier = Modifier,
    scope: CurveCutNavBarScope,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    onClick: () -> Unit = {},
    content: @Composable CurveCutNavBarScope.() -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.size(FabRadius.times(2)),
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = FabElevation,
            pressedElevation = FabPressedElevation
        ),
        content = { scope.content() }
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
    content: @Composable CurveCutNavBarScope.() -> Unit
) {
    Surface(
        modifier = modifier
            .height(BottomNavigationHeight)
            .zIndex(1f),
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        shape = shape
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            content = { scope.content() }
        )
    }
}

private enum class CurveCutSlots { FAB, BOTTOM_NAV_BAR }

@Composable
private fun curveCutShape(offsetX: Dp): CutOutShape {
    val fabDiameter = FabRadius.times(2).toPx()
    return CutOutShape(
        cutOutShape = BottomNavBarCutOutShape,
        cutOutShapeMargin = FabMargin.toPx(),
        cutoutStartOffset = animateDpAsState(
            targetValue = offsetX,
            animationSpec = CurveCutBezierEasing
        ).value.toPx(),
        cutOutShapeSize = Size(
            width = fabDiameter,
            height = fabDiameter
        ),
        cutOutDepth = CutOutDepthMargin.toPx(),
        cutOutEdgeRadius = 4f,
        smoothEdge = false
    )
}

@Stable
class CurveCutNavBarState(
    defaultSelectedItem: Int = 0,
    val maxItems: Int
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

private class FabPlacement(
    val isDocked: Boolean,
    val offsetX: Dp,
    val offsetY: Dp
)

@Composable
@Suppress("SameParameterValue")
private fun animateEmerge(
    startOffset: Dp,
    emergedDepth: Dp,
    subMergedDepth: Dp
): FabPlacement {
    val pathCovered = animateDpAsState(
        targetValue = startOffset,
        animationSpec = CurveCutBezierEasing
    ).value

    val offsetY: Dp = if (pathCovered == startOffset) emergedDepth else subMergedDepth
    val heightOffset = animateDpAsState(
        targetValue = offsetY,
        animationSpec = CurveCutBezierEasing
    ).value

    return FabPlacement(
        isDocked = offsetY == emergedDepth,
        offsetX = pathCovered,
        offsetY = heightOffset
    )
}

private val BottomNavBarCutOutShape = object : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            addCutOutShape(size)
        }

        return Outline.Generic(path)
    }

    @Suppress("UnnecessaryVariable")
    private fun Path.addCutOutShape(size: Size) {
        // To create bottomNavigation curve cutout shape, we will divide curve in two halves
        // curve size contains width of each curve, and curve height is the depth of cutout.
        // i.e half of the total size
        val curveWidth = size.width / 2
        val curveDepth = size.height / 2

        // Shift start and end offset, to avoid overlapping cutout shape with fab
        // because to smooth the drop in curve it will draw towards the fab.
        val startOffset = -curveWidth / 2
        val endOffset = curveWidth / 2

        // Offsets for Control points
        val topControlPoint = Offset(curveWidth, curveDepth)
        val bottomControlPoint = Offset(curveWidth, 0f)

        // FirstCurve start and end points, starting point is the curve topLeft co-ordinate
        // end point is the depth of curve and the start point for second curve
        val firstCurveStart = Offset(x = startOffset, y = curveDepth) // P1
        val firstCurveEnd = Offset(x = curveWidth, y = size.height) // P2

        // Control points for first curve
        val firstCurveControlPoint1 = Offset(
            x = firstCurveStart.x + topControlPoint.x + startOffset.div(2),
            y = topControlPoint.y
        ) // C1
        val firstCurveControlPoint2 = Offset(
            x = firstCurveEnd.x - bottomControlPoint.x,
            y = firstCurveEnd.y - bottomControlPoint.y
        ) // C2

        // SecondCurve start and end points, starting point is the endpoint of first curve and
        // end point is the topRight co-ordinate
        val secondCurveStart = Offset(firstCurveEnd.x, firstCurveEnd.y) // P2
        val secondCurveEnd = Offset(x = size.width + endOffset, y = curveDepth) // P3

        // Control points for second curve
        val secondCurveControlPoint1 = Offset(
            x = secondCurveStart.x + bottomControlPoint.x,
            y = secondCurveStart.y - bottomControlPoint.y
        ) // C4
        val secondCurveControlPoint2 = Offset(
            x = secondCurveEnd.x - topControlPoint.x + endOffset.div(2),
            y = topControlPoint.y
        ) // C3

        // Start point of curve
        moveTo(x = firstCurveStart.x, y = firstCurveStart.y)

        // First bezier curve with (P1, C1, C2, P2)
        cubicTo(
            x1 = firstCurveControlPoint1.x,
            y1 = firstCurveControlPoint1.y,
            x2 = firstCurveControlPoint2.x,
            y2 = firstCurveControlPoint2.y,
            x3 = firstCurveEnd.x,
            y3 = firstCurveEnd.y
        )

        // Second bezier curve with (P2, C4, C3, P3)
        cubicTo(
            x1 = secondCurveControlPoint1.x,
            y1 = secondCurveControlPoint1.y,
            x2 = secondCurveControlPoint2.x,
            y2 = secondCurveControlPoint2.y,
            x3 = secondCurveEnd.x,
            y3 = secondCurveEnd.y
        )
        // complete the path
        close()
    }
}

private val CurveCutBezierEasing = TweenSpec<Dp>(
    durationMillis = 300,
    easing = FastOutSlowInEasing
)

private val BottomNavigationHeight = 56.dp

private val FabRadius = 56.dp.div(2)

private val FabMargin = 8.dp

private val FabDepth = 16.dp

private val CurveCutBottomNavigationHeight = BottomNavigationHeight + FabRadius + FabMargin

private val CutOutDepthMargin = 6.dp

private val BottomNavigationElevation = 4.dp

private val FabElevation = 12.dp

private val FabPressedElevation = 6.dp

private val NoIcon: @Composable CurveCutNavBarScope.() -> Unit = {}
