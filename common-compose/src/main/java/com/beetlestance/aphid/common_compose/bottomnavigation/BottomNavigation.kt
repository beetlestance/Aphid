package com.beetlestance.aphid.common_compose.bottomnavigation

import android.graphics.PointF
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animate
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.VectorizedAnimationSpec
import androidx.compose.foundation.AmbientContentColor
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonConstants
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Layout
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.AnimationClockAmbient
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlin.math.roundToInt

/**
 *  Taken from a wonderful detailed article about creating curved cut bottom navigation from
 *  https://medium.com/swlh/curved-cut-out-bottom-navigation-with-animation-in-android-c630c867958c
 */

/**
 * @param defaultSelectedItem is the first selected item
 */
@Stable
class CurvedCutBottomNavigationState(
    defaultSelectedItem: Int
) {
    // state to remember selected item
    private var _selectedItem by mutableStateOf(defaultSelectedItem)
    var selectedItem: Int
        get() = _selectedItem
        set(value) {
            _selectedItem = value
        }

    // icon for the current selected position
    private var _selectedItemIcon: (@Composable () -> Unit) by mutableStateOf({})
    var selectedItemIcon: (@Composable () -> Unit)
        get() = _selectedItemIcon
        set(value) {
            _selectedItemIcon = value
        }
}

@Composable
fun CurvedCutBottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    fabBackgroundColor: Color = MaterialTheme.colors.primarySurface,
    elevation: Dp = BottomNavigationElevation,
    defaultSelection: Int = 0,
    menuItems: Int,
    content: @Composable (CurvedCutBottomNavigationState) -> Unit
) {
    BottomNavigationColor = backgroundColor
    val fabRadius: Float = DensityAmbient.current.density * FabRadius.value
    val curveBottomOffset = DensityAmbient.current.density * CurvedBottomNavigationOffset.value
    val layoutHeight = BottomNavigationHeight + FabRadius

    Box(modifier = modifier.clipToBounds()) {
        WithConstraints {
            val state: CurvedCutBottomNavigationState = remember {
                CurvedCutBottomNavigationState(defaultSelection)
            }

            layoutSize = IntSize(
                width = constraints.maxWidth,
                height = (layoutHeight.value * DensityAmbient.current.density).toInt()
            )

            val menuItemWidth = constraints.maxWidth / menuItems
            val menuItemCenterX = menuItemWidth / 2
            val cellCentreOffsetX = menuItemWidth * state.selectedItem + menuItemCenterX
            val currentOffsetX = cellCentreOffsetX.toFloat()
            val currentFabOffsetX =
                (cellCentreOffsetX / DensityAmbient.current.density).dp - FabRadius

            val menuItemOffsetX = animate(
                target = currentOffsetX,
                animSpec = remember { bottomNavigationAnimationSpec() }
            )

            val fabOffsetX = animate(target = currentFabOffsetX)

            val fabOffsetY =
                animate(target = if (fabOffsetX == currentFabOffsetX) 8.dp else layoutHeight)

            val path = computeCurve(
                menuItemOffsetX,
                curveBottomOffset,
                fabRadius,
                fabRadius
            )

            Box {
                // have to provide click behaviour in case to reset the nav controller destination.
                FloatingActionButton(
                    onClick = {},
                    modifier = Modifier.size(FabRadius.times(2))
                        .offset(x = fabOffsetX, y = fabOffsetY),
                    backgroundColor = fabBackgroundColor,
                    elevation = FloatingActionButtonConstants.defaultElevation(
                        defaultElevation = FabElevation,
                        pressedElevation = FabPressedElevation
                    ),
                    icon = state.selectedItemIcon
                )

                Surface(
                    modifier = Modifier.align(Alignment.BottomCenter).preferredHeight(layoutHeight),
                    color = backgroundColor,
                    elevation = elevation,
                    shape = object : Shape {
                        override fun createOutline(size: Size, density: Density): Outline {
                            return Outline.Generic(path)
                        }
                    }
                ) {
                    // this can now be replaced by Row or any other composable
                    // The only problem the handling fab button click for selected item
                    // TODO: Replace with [Row]
                    Layout(
                        modifier = Modifier.fillMaxWidth()
                            .preferredHeight(layoutHeight),
                        children = { content(state) }
                    ) { measurables, constraints ->
                        layout(constraints.maxWidth, constraints.maxHeight) {
                            // Place navigation menu items
                            measurables.forEachIndexed { index, measurable ->
                                // set width of menu item
                                val placeable = measurable.measure(
                                    constraints.copy(
                                        minWidth = menuItemWidth,
                                        minHeight = constraints.maxHeight - fabRadius.roundToInt()
                                    )
                                )

                                val offset = IntOffset(
                                    x = index * menuItemWidth,
                                    y = (fabRadius / 2).roundToInt()
                                )

                                placeable.place(offset)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CurvedCutBottomNavigationItem(
    index: Int,
    icon: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
    state: CurvedCutBottomNavigationState,
    modifier: Modifier = Modifier
) {
    // TODO: Replace with [BottomNavigationItem]
    // Have to first replace Layout with Row in CurvedCutBottomNavigation
    Box(
        modifier = modifier.selectable(
            selected = selected,
            onClick = onClick,
            indication = null
        ).fillMaxHeight(),
        alignment = Alignment.Center,
        children = { if (selected.not()) icon() }
    )

    if (selected) {
        state.selectedItem = index
        state.selectedItemIcon = icon
    }
}

@Composable
private fun Modifier.drawCurve(path: Path) = drawWithContent {
    drawPath(path, SolidColor(BottomNavigationColor))
    drawContent()
}

fun computeCurve(
    offsetX: Float,
    curveBottomOffset: Float,
    bottomNavOffsetY: Float,
    fabRadius: Float
): Path {
    val path = Path()

    // first bezier curve
    val firstCurveStart = PointF()
    val firstCurveEnd = PointF()
    val firstCurveControlPoint1 = PointF()
    val firstCurveControlPoint2 = PointF()

    // second bezier curve
    val secondCurveStart = PointF()
    val secondCurveEnd = PointF()
    val secondCurveControlPoint1 = PointF()
    val secondCurveControlPoint2 = PointF()

    // Max height and width
    val width: Int = layoutSize.width
    val height: Int = layoutSize.height

    // offset of the first control point (top part)
    val topControlX = fabRadius + fabRadius.div(2)
    val topControlY = bottomNavOffsetY + fabRadius.div(6)

    // offset of the second control point (bottom part)
    val bottomControlX = fabRadius + fabRadius.div(2)
    val bottomControlY = fabRadius.div(4)

    // width of the curve
    val fabMargin = height - fabRadius.times(2) - curveBottomOffset
    val curveHalfWidth = fabRadius * 2 + fabMargin

    // first curve
    // set the starting point of the curve (P2)
    firstCurveStart.apply {
        x = offsetX - curveHalfWidth
        y = bottomNavOffsetY
    }
    // set the end point for the first curve (P3)
    firstCurveEnd.apply {
        x = offsetX - 16
        y = height - curveBottomOffset - 4
    }
    // set the first control point (C1)
    firstCurveControlPoint1.apply {
        x = firstCurveStart.x + topControlX
        y = topControlY
    }
    // set the second control point (C2)
    firstCurveControlPoint2.apply {
        x = firstCurveEnd.x - bottomControlX
        y = firstCurveEnd.y - bottomControlY
    }

    // second curve
    // end of first curve and start of second curve is the same (P3)
    secondCurveStart.set(offsetX + 16, firstCurveEnd.y)
    // end of the second curve (P4)
    secondCurveEnd.apply {
        x = offsetX + curveHalfWidth
        y = bottomNavOffsetY
    }
    // set the first control point of second curve (C4)
    secondCurveControlPoint1.apply {
        x = secondCurveStart.x + bottomControlX
        y = secondCurveStart.y - bottomControlY
    }
    // set the second control point (C3)
    secondCurveControlPoint2.apply {
        x = secondCurveEnd.x - topControlX
        y = topControlY
    }

    // clear any previous path
    path.reset()
    // start from P1 of the BottomNavigationView
    path.moveTo(0f, bottomNavOffsetY)
    // horizontal line from P1 to P2
    path.lineTo(firstCurveStart.x, firstCurveStart.y)
    // bezier curve with (P2, C1, C2, P3)
    path.cubicTo(
        firstCurveControlPoint1.x,
        firstCurveControlPoint1.y,
        firstCurveControlPoint2.x,
        firstCurveControlPoint2.y,
        firstCurveEnd.x,
        firstCurveEnd.y
    )

    path.quadraticBezierTo(
        offsetX,
        height - curveBottomOffset - 2,
        secondCurveStart.x,
        secondCurveStart.y
    )
    // bezier curve with (P3, C4, C3, P4)
    path.cubicTo(
        secondCurveControlPoint1.x,
        secondCurveControlPoint1.y,
        secondCurveControlPoint2.x,
        secondCurveControlPoint2.y,
        secondCurveEnd.x,
        secondCurveEnd.y
    )
    // line from P4 to P5
    path.lineTo(width.toFloat(), bottomNavOffsetY)
    // line from P5 to P6
    path.lineTo(width.toFloat(), height.toFloat())
    // line from P6 to P7
    path.lineTo(0f, height.toFloat())
    // complete the path
    path.close()

    return path
}

/**
 * [VectorizedAnimationSpec] controlling the transition between unselected and selected
 * [BottomNavigationItem]s.
 */
private fun bottomNavigationAnimationSpec() = TweenSpec<Float>(
    durationMillis = 400,
    easing = CubicBezierEasing(0.2f, 0f, 0.8f, 1f)
)

private val BottomNavigationHeight = 56.dp

private val CurvedBottomNavigationOffset = 12.dp

private val BottomNavigationElevation = 8.dp

private var BottomNavigationColor = Color.White

private val FabRadius = 56.dp.div(2)

private val FabElevation = 12.dp

private val FabPressedElevation = 6.dp

private var layoutSize: IntSize = IntSize.Zero


