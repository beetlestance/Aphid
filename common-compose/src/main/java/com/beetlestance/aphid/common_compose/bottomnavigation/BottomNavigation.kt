package com.beetlestance.aphid.common_compose.bottomnavigation

import androidx.annotation.FloatRange
import androidx.compose.animation.animate
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.VectorizedAnimationSpec
import androidx.compose.foundation.AmbientContentColor
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.ProvideTextStyle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.LastBaseline
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
import androidx.compose.runtime.emptyContent
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Layout
import androidx.compose.ui.MeasureScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.Placeable
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.id
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max
import kotlin.math.roundToInt

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
    label: @Composable () -> Unit = emptyContent(),
    alwaysShowLabels: Boolean = true,
    interactionState: InteractionState = remember { InteractionState() },
    selectedContentColor: Color = AmbientContentColor.current,
    unselectedContentColor: Color = AmbientEmphasisLevels.current.medium.applyEmphasis(
        selectedContentColor
    )
) {
    val styledLabel = @Composable {
        val style = MaterialTheme.typography.caption.copy(textAlign = TextAlign.Center)
        ProvideTextStyle(style, children = label)
    }
    // The color of the Ripple should always the selected color, as we want to show the color
    // before the item is considered selected, and hence before the new contentColor is
    // provided by BottomNavigationTransition.
    val ripple = RippleIndication(bounded = false, color = selectedContentColor)

    // TODO This composable has magic behavior within a Row; reconsider this behavior later
    Box(
        with(RowScope) {
            modifier
                .selectable(
                    selected = selected,
                    onClick = onClick,
                    interactionState = interactionState,
                    indication = ripple
                )
                .weight(1f)
        },
        alignment = Alignment.Center
    ) {
        CurvedCutBottomNavigationTransition(
            selectedContentColor,
            unselectedContentColor,
            selected
        ) { progress ->
            val animationProgress = if (alwaysShowLabels) 1f else progress

            CurvedCutBottomNavigationItemBaselineLayout(
                icon = icon,
                label = styledLabel,
                iconPositionAnimationProgress = animationProgress
            )
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

@Composable
private fun CurvedCutBottomNavigationItemBaselineLayout(
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    @FloatRange(from = 0.0, to = 1.0) iconPositionAnimationProgress: Float
) {
    Layout(
        {
            Box(Modifier.layoutId("icon")) { icon() }
            Box(
                Modifier
                    .layoutId("label")
                    .drawOpacity(iconPositionAnimationProgress)
                    .padding(horizontal = BottomNavigationItemHorizontalPadding)
            ) { label() }
        }
    ) { measurables, constraints ->
        val iconPlaceable = measurables.first { it.id == "icon" }.measure(constraints)

        val labelPlaceable = measurables.first { it.id == "label" }.measure(
            // Measure with loose constraints for height as we don't want the label to take up more
            // space than it needs
            constraints.copy(minHeight = 0)
        )

        // If the label is empty, just place the icon.
        if (labelPlaceable.width <= BottomNavigationItemHorizontalPadding.toIntPx() * 2 &&
            labelPlaceable.height == 0
        ) {
            placeIcon(iconPlaceable, constraints)
        } else {
            placeLabelAndIcon(
                labelPlaceable,
                iconPlaceable,
                constraints,
                iconPositionAnimationProgress
            )
        }
    }
}

/**
 * Places the provided [iconPlaceable] in the vertical center of the provided [constraints]
 */
private fun MeasureScope.placeIcon(
    iconPlaceable: Placeable,
    constraints: Constraints
): MeasureScope.MeasureResult {
    val height = constraints.maxHeight
    val iconY = (height - iconPlaceable.height) / 2
    return layout(iconPlaceable.width, height) {
        iconPlaceable.placeRelative(0, iconY)
    }
}

private fun MeasureScope.placeLabelAndIcon(
    labelPlaceable: Placeable,
    iconPlaceable: Placeable,
    constraints: Constraints,
    @FloatRange(from = 0.0, to = 1.0) iconPositionAnimationProgress: Float
): MeasureScope.MeasureResult {
    val height = constraints.maxHeight

    // have a better strategy than overlapping the icon and label
    val baseline = labelPlaceable[LastBaseline]

    val baselineOffset = CombinedItemTextBaseline.toIntPx()

    // Label should be [baselineOffset] from the bottom
    val labelY = height - baseline - baselineOffset

    val unselectedIconY = (height - iconPlaceable.height) / 2

    // Icon should be [baselineOffset] from the text baseline, which is itself
    // [baselineOffset] from the bottom
    val selectedIconY = height - (baselineOffset * 2) - iconPlaceable.height

    val containerWidth = max(labelPlaceable.width, iconPlaceable.width)

    val labelX = (containerWidth - labelPlaceable.width) / 2
    val iconX = (containerWidth - iconPlaceable.width) / 2

    // How far the icon needs to move between unselected and selected states
    val iconDistance = unselectedIconY - selectedIconY

    // When selected the icon is above the unselected position, so we will animate moving
    // downwards from the selected state, so when progress is 1, the total distance is 0, and we
    // are at the selected state.
    val offset = (iconDistance * (1 - iconPositionAnimationProgress)).roundToInt()

    return layout(containerWidth, height) {
        if (iconPositionAnimationProgress != 0f) {
            labelPlaceable.placeRelative(labelX, labelY + offset)
        }
        iconPlaceable.placeRelative(iconX, selectedIconY + offset)
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

/**
 * Padding at the start and end of a [BottomNavigationItem]
 */
private val BottomNavigationItemHorizontalPadding = 12.dp

/**
 * The space between the text baseline and the bottom of the [BottomNavigationItem], and between
 * the text baseline and the bottom of the icon placed above it.
 */
private val CombinedItemTextBaseline = 12.dp
