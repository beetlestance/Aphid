package com.beetlestance.aphid.common_compose.bottomnavigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animate
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.VectorizedAnimationSpec
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonConstants
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticAmbientOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMap
import com.beetlestance.aphid.common_compose.extensions.toDp
import com.beetlestance.aphid.common_compose.extensions.toPx
import com.beetlestance.aphid.common_compose.utils.CurveCut
import com.beetlestance.aphid.common_compose.utils.computeCurve

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
    fabIcon: @Composable () -> Unit,
    menuItems: @Composable CurveCutNavBarScope.() -> Unit
) {

    val state = remember { CurveCutNavBarState(selectedItem) }

    SubcomposeLayout<CurveCutSlots>(
        modifier = modifier
    ) { constraints ->

        val menuItemWidth = constraints.maxWidth.div(maxItems)

        val menuItemCenterX = menuItemWidth / 2
        val cellCentreOffsetX = menuItemWidth * state.selectedItem + menuItemCenterX

        val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)

        val navOffsetY = constraints.maxHeight - BottomNavigationHeight.toIntPx()

        layout(constraints.maxWidth, constraints.maxHeight) {

            val fabPlaceables = subcompose(CurveCutSlots.FAB) {
                CurveCutFab(
                    backgroundColor = fabBackgroundColor,
                    contentColor = fabContentColor,
                    fabIcon = fabIcon
                )
            }.fastMap { it.measure(looseConstraints) }

            val fabPlacement = FabPlacement(
                offsetX = cellCentreOffsetX.toDp() - FabRadius,
                offsetY = 8.dp
            )

            val bottomNavBarPlaceables = subcompose(CurveCutSlots.BOTTOM_NAV_BAR) {
                Providers(
                    AmbientFabPlacement provides fabPlacement,
                    children = {
                        CurveCutNavBarScope(state).CurveCutBottomNavBar(
                            shape = CurveCutBottomNavBarShape(
                                offsetX = animate(target = cellCentreOffsetX.toDp())
                            ),
                            backgroundColor = backgroundColor,
                            contentColor = contentColor,
                            elevation = elevation,
                            menuItems = menuItems
                        )
                    }
                )
            }.fastMap {
                it.measure(looseConstraints)
            }

            // The bottom bar is always at the bottom of the layout
            bottomNavBarPlaceables.fastForEach {
                it.place(0, navOffsetY)
            }

            fabPlaceables.fastForEach {
                it.place(
                    fabPlacement.offsetX.toIntPx(),
                    navOffsetY - fabPlacement.offsetY.toIntPx()
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CurveCutNavBarScope.CurveCutMenuItem(
    index: Int,
    icon: @Composable () -> Unit,
    fabIcon: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (selected) {
        state.selectedItem = index
    }
    Box(
        modifier = modifier.size(48.dp)
            .selectable(
                selected = selected,
                onClick = onClick,
                indication = null
            ),
        alignment = Alignment.Center,
        children = { icon() }
    )
}

@Composable
private fun CurveCutFab(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    onClick: () -> Unit = {},
    fabIcon: @Composable() () -> Unit
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
        icon = fabIcon
    )
}

@Composable
private fun CurveCutNavBarScope.CurveCutBottomNavBar(
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    shape: Shape,
    elevation: Dp = BottomNavigationElevation,
    menuItems: @Composable CurveCutNavBarScope.() -> Unit
) {
    Surface(
        modifier = Modifier.preferredHeight(BottomNavigationHeight),
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        shape = shape
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            children = { this@CurveCutBottomNavBar.menuItems() }
        )
    }
}

private enum class CurveCutSlots { FAB, BOTTOM_NAV_BAR }

@Immutable
private class FabPlacement(
    val offsetX: Dp,
    val offsetY: Dp
)

private val AmbientFabPlacement = staticAmbientOf<FabPlacement?> { null }

private class CurveCutBottomNavBarShape(
    val offsetX: Dp
) : Shape {

    override fun createOutline(size: Size, density: Density): Outline {
        val boundingRectangle = Rect(0f, 0f, size.width, size.height)

        val path = boundingRectangle.computeCurve(
            with(density) { offsetX.toPx() },
            with(density) { CurvedBottomNavigationOffset.toPx() },
            with(density) { FabRadius.toPx() }
        )

        return Outline.Generic(path)
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
    val state: CurveCutNavBarState
)

/**
 *  Taken from a wonderful detailed article about creating curved cut bottom navigation from
 *  https://medium.com/swlh/curved-cut-out-bottom-navigation-with-animation-in-android-c630c867958c
 */
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
    val state: CurvedCutBottomNavigationState =
        remember { CurvedCutBottomNavigationState(defaultSelectedItem = defaultSelection) }

    val curveBottomOffset = CurvedBottomNavigationOffset.toPx()

    WithConstraints(modifier = modifier.clipToBounds()) {
        val menuItemWidth = constraints.maxWidth / menuItems

        val layoutSize = Size(
            width = constraints.maxWidth.toFloat(),
            height = CurveCutBottomNavigationHeight.toPx()
        )

        val menuItemCenterX = menuItemWidth / 2
        val cellCentreOffsetX = menuItemWidth * state.selectedItem + menuItemCenterX
        val currentOffsetX = cellCentreOffsetX.toFloat()
        val currentFabOffsetX = cellCentreOffsetX.toDp() - FabRadius

        val menuItemOffsetX = animate(
            target = currentOffsetX,
            animSpec = BottomNavigationAnimationSpec
        )

        val fabOffsetX = animate(target = currentFabOffsetX)

        val fabIsInPosition = fabOffsetX == currentFabOffsetX

        val fabOffsetY = animate(if (fabIsInPosition) 8.dp else CurveCutBottomNavigationHeight)

        val rect = Rect(
            offset = Offset(x = 0f, y = FabRadius.plus(FabDepthMargin).toPx()),
            size = layoutSize
        )

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
            icon = if (fabIsInPosition) state.selectedItemIcon else NoIcon
        )

        Surface(
            modifier = Modifier.preferredHeight(CurveCutBottomNavigationHeight),
            color = backgroundColor,
            elevation = elevation,
            shape = CurveCut(
                rect = rect,
                offsetX = menuItemOffsetX,
                curveBottomOffset = curveBottomOffset,
                radius = FabRadius.toPx()
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().height(BottomNavigationHeight).offset(
                    x = 0.dp, y = FabRadius - FabDepthMargin
                ),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                children = { content(state) }
            )
        }

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CurvedCutBottomNavigationItem(
    index: Int,
    icon: @Composable () -> Unit,
    fabIcon: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
    state: CurvedCutBottomNavigationState,
    modifier: Modifier = Modifier
) {
    if (selected) {
        state.selectedItem = index
        state.selectedItemIcon = fabIcon
    }

    Box(
        modifier = modifier.selectable(
            selected = selected,
            onClick = onClick,
            indication = null
        ),
        children = { icon() }
    )
}


/**
 * @param defaultSelectedItem is the first selected item
 */
@Stable
class CurvedCutBottomNavigationState(
    defaultSelectedItem: Int = 0
) {
    // state to remember selected item
    private var _selectedItem by mutableStateOf(defaultSelectedItem)
    var selectedItem: Int
        get() = _selectedItem
        set(value) {
            _selectedItem = value
        }

    // icon for the current selected position
    private var _selectedItemIcon: (@Composable () -> Unit) by mutableStateOf(NoIcon)
    var selectedItemIcon: (@Composable () -> Unit)
        get() = _selectedItemIcon
        set(value) {
            _selectedItemIcon = value
        }
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
    easing = CubicBezierEasing(0.2f, 0f, 0.8f, 1f)
)

private val BottomNavigationHeight = 56.dp

internal val FabRadius = 56.dp.div(2)

private val FabDepthMargin = 8.dp

private val CurveCutBottomNavigationHeight = BottomNavigationHeight + FabRadius + FabDepthMargin

internal val CurvedBottomNavigationOffset = 12.dp

private val BottomNavigationElevation = 8.dp

private val FabElevation = 12.dp

private val FabPressedElevation = 6.dp

internal val NoIcon: @Composable () -> Unit = {}

