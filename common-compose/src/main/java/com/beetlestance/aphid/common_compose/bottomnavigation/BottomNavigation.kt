package com.beetlestance.aphid.common_compose.bottomnavigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animate
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.VectorizedAnimationSpec
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.beetlestance.aphid.common_compose.utils.CurveCut
import com.beetlestance.aphid.common_compose.utils.toDp
import com.beetlestance.aphid.common_compose.utils.toPx
import kotlin.math.roundToInt

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
    val curveBottomOffset = CurvedBottomNavigationOffset.toPx()

    WithConstraints(modifier = modifier.clipToBounds()) {
        val state: CurvedCutBottomNavigationState =
            remember { CurvedCutBottomNavigationState(defaultSelection) }

        val menuItemWidth = constraints.maxWidth / menuItems
        val fabRadius = FabRadius.toPx()

        val layoutHeight = BottomNavigationHeight + fabRadius.toDp()

        val layoutSize = Size(
            width = constraints.maxWidth.toFloat(),
            height = layoutHeight.toPx()
        )

        val menuItemCenterX = menuItemWidth / 2
        val cellCentreOffsetX = menuItemWidth * state.selectedItem + menuItemCenterX
        val currentOffsetX = cellCentreOffsetX.toFloat()
        val currentFabOffsetX = cellCentreOffsetX.toFloat().toDp() - fabRadius.toDp()

        val menuItemOffsetX = animate(
            target = currentOffsetX,
            animSpec = remember { bottomNavigationAnimationSpec() }
        )

        val fabOffsetX = animate(target = currentFabOffsetX)

        val fabIsInPosition = fabOffsetX == currentFabOffsetX

        val fabOffsetY = animate(if (fabIsInPosition) 8.dp else layoutHeight)

        val rect = Rect(offset = Offset(x = 0f, y = fabRadius), size = layoutSize)

        // have to provide click behaviour in case to reset the nav controller destination.
        FloatingActionButton(
            onClick = {},
            modifier = Modifier.size(fabRadius.toDp().times(2))
                .offset(x = fabOffsetX, y = fabOffsetY),
            backgroundColor = fabBackgroundColor,
            elevation = FloatingActionButtonConstants.defaultElevation(
                defaultElevation = FabElevation,
                pressedElevation = FabPressedElevation
            ),
            icon = if (fabIsInPosition) state.selectedItemIcon else ({})
        )

        Surface(
            modifier = Modifier.preferredHeight(layoutHeight),
            color = backgroundColor,
            elevation = elevation,
            shape = CurveCut(
                rect = rect,
                offsetX = menuItemOffsetX,
                curveBottomOffset = curveBottomOffset,
                radius = fabRadius
            )
        ) {
            // this can now be replaced by Row or any other composable
            // The only problem the handling fab button click for selected item
            // TODO: Replace with [Row]
            Layout(
                modifier = Modifier.fillMaxWidth().preferredHeight(layoutHeight),
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

    // TODO: Replace with [BottomNavigationItem]
    // Have to first replace Layout with Row in CurvedCutBottomNavigation
    Box(
        modifier = modifier.selectable(
            selected = selected,
            onClick = onClick,
            indication = null
        ).fillMaxHeight(),
        alignment = Alignment.Center
    ) {
        icon()
    }
}


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

/**
 * [VectorizedAnimationSpec] controlling the transition between unselected and selected
 * [BottomNavigationItem]s.
 */
private fun bottomNavigationAnimationSpec() = TweenSpec<Float>(
    durationMillis = 300,
    easing = CubicBezierEasing(0.2f, 0f, 0.8f, 1f)
)

private val BottomNavigationHeight = 56.dp

private val CurvedBottomNavigationOffset = 12.dp

private val BottomNavigationElevation = 8.dp

private val FabRadius = 56.dp.div(2)

private val FabElevation = 12.dp

private val FabPressedElevation = 6.dp

