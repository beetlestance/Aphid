package com.beetlestance.aphid.common_compose.bottomnavigation

import android.graphics.PointF
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.VectorizedAnimationSpec
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Layout
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.Density
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
    elevation: Dp = BottomNavigationElevation,
    content: @Composable () -> Unit
) {
    fabRadius = Density(ContextAmbient.current).density * 36
    bottomNavigationColor = backgroundColor

    Surface(
        color = Color.Transparent,
        elevation = elevation,
        modifier = modifier
    ) {
        Layout(
            modifier = Modifier.fillMaxWidth().preferredHeight(BottomNavigationHeight).setCurve(),
            children = content
        ) { measurables, constraints ->
            layout(constraints.maxWidth, constraints.maxHeight) {
                // Calculate single item size
                val menuItemWidth = constraints.maxWidth / measurables.size

                // Calculate center x for curve
                val menuItemCenterX = menuItemWidth / 2
                val centerOffsetX = menuItemWidth * selectedItem + menuItemCenterX

                // Compute curve for first time
                computeCurve(centerOffsetX, constraints.maxWidth, constraints.maxHeight)

                // Place navigation menu items
                measurables.forEachIndexed { index, measurable ->
                    // set width of menu item
                    val placeable = measurable.measure(constraints.copy(minWidth = menuItemWidth))
                    placeable.place(x = index * menuItemWidth, y = 0)
                }
            }
        }
    }
}

@Composable
fun CurvedCutBottomNavigationItem(
    icon: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Int,
    modifier: Modifier = Modifier,
    interactionState: InteractionState = remember { InteractionState() },
) {

    Box(
        modifier = modifier.selectable(
            selected = selected,
            onClick = { selectedItem = onClick() },
            interactionState = interactionState,
            indication = null
        ).fillMaxHeight(),
        alignment = Alignment.Center,
        children = { icon() }
    )
}

@Composable
private fun Modifier.setCurve() = drawWithContent {
    drawIntoCanvas {
        it.drawPath(path, navPaint)
    }
    drawContent()
}

private fun computeCurve(offsetX: Int, width: Int, height: Int) {
    // offset of the first control point (top part)
    val topControlX = fabRadius + fabRadius.div(2)
    val topControlY = fabRadius.div(6)

    // offset of the second control point (bottom part)
    val bottomControlX = fabRadius + fabRadius.div(2)
    val bottomControlY = fabRadius.div(4)

    // width of the curve
    val curveOffset = fabRadius * 2 + (fabRadius / 6)

    // first curve
    // set the starting point of the curve (P2)
    firstCurveStart.apply {
        // we want the curve to start at CURVE_OFFSET before the center of the view
        x = offsetX - curveOffset
        y = 0f
    }
    // set the end point for the first curve (P3)
    firstCurveEnd.apply {
        x = offsetX.toFloat()
        y = fabRadius + (fabRadius / 4)
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
    secondCurveStart.set(firstCurveEnd.x, firstCurveEnd.y)
    // end of the second curve (P4)
    secondCurveEnd.apply {
        x = offsetX + curveOffset
        y = 0f
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
    path.moveTo(0f, 0f)
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
    path.lineTo(width.toFloat(), 0f)
    // line from P5 to P6
    path.lineTo(width.toFloat(), height.toFloat())
    // line from P6 to P7
    path.lineTo(0f, height.toFloat())
    // complete the path
    path.close()
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

private var bottomNavigationColor = Color.White

private var selectedItem: Int = 1

// the radius of the FloatingActionButton
private var fabRadius = 0f

// first bezier curve
private val firstCurveStart = PointF()
private val firstCurveEnd = PointF()
private val firstCurveControlPoint1 = PointF()
private val firstCurveControlPoint2 = PointF()

// second bezier curve
private val secondCurveStart = PointF()
private val secondCurveEnd = PointF()
private val secondCurveControlPoint1 = PointF()
private val secondCurveControlPoint2 = PointF()

// path to represent the background including the curve
private val path: Path = Path()

private val navPaint = Paint().apply {
    style = PaintingStyle.Fill
    color = bottomNavigationColor
}
