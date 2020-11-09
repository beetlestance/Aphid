package com.beetlestance.aphid.common_compose.bottomnavigation

import androidx.compose.animation.animate
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.VectorizedAnimationSpec
import androidx.compose.foundation.AmbientContentColor
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope.Companion.weight
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.AmbientEmphasisLevels
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.contentColorFor
import androidx.compose.material.primarySurface
import androidx.compose.material.ripple.RippleIndication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 *  Taken from a wonderful detailed article about creating curved cut bottom navigation from
 *  https://medium.com/swlh/curved-cut-out-bottom-navigation-with-animation-in-android-c630c867958c
 */
@Composable
fun CurvedCutBottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = BottomNavigationElevation,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        modifier = modifier
    ) {
        Row(
            Modifier.fillMaxWidth().preferredHeight(BottomNavigationHeight),
            horizontalArrangement = Arrangement.SpaceBetween,
            children = content
        )
    }
}

@Composable
fun CurvedCutBottomNavigationItem(
    icon: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    interactionState: InteractionState = remember { InteractionState() },
    selectedContentColor: Color = AmbientContentColor.current,
    unselectedContentColor: Color = AmbientEmphasisLevels.current.medium.applyEmphasis(
        selectedContentColor
    )
) {
    // The color of the Ripple should always the selected color, as we want to show the color
    // before the item is considered selected, and hence before the new contentColor is
    // provided by BottomNavigationTransition.
    val ripple = RippleIndication(bounded = false, color = selectedContentColor)

    // TODO This composable has magic behavior within a Row; reconsider this behavior later
    Row(
        modifier = modifier.selectable(
            selected = selected,
            onClick = onClick,
            interactionState = interactionState,
            indication = ripple
        ).fillMaxHeight().weight(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CurvedCutBottomNavigationTransition(
            selectedContentColor,
            unselectedContentColor,
            selected
        ) { progress ->
            icon()
        }
    }
}

@Composable
private fun CurvedCutBottomNavigationTransition(
    activeColor: Color,
    inactiveColor: Color,
    selected: Boolean,
    content: @Composable (animationProgress: Float) -> Unit
) {
    val animationProgress = animate(
        target = if (selected) 1f else 0f,
        animSpec = BottomNavigationAnimationSpec
    )

    val color = lerp(inactiveColor, activeColor, animationProgress)

    Providers(AmbientContentColor provides color) {
        content(animationProgress)
    }
}

/**
 * [VectorizedAnimationSpec] controlling the transition between unselected and selected
 * [BottomNavigationItem]s.
 */
private val BottomNavigationAnimationSpec = TweenSpec<Float>(
    durationMillis = 300,
    easing = FastOutSlowInEasing
)

/**
 * Height of a [BottomNavigation] component
 */
private val BottomNavigationHeight = 56.dp

/**
 * Default elevation of a [BottomNavigation] component
 */
private val BottomNavigationElevation = 8.dp
