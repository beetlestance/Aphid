package com.beetlestance.aphid.common_compose.utils


import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path

/**
 * bezier curve calculator [https://www.desmos.com/calculator/d1ofwre0fr]
 */
@Stable
fun Rect.computeCurve(
    offsetX: Float,
    curveBottomOffset: Float,
    bottomNavOffsetY: Float,
    radius: Float
): Path {
    val path = Path()

    // width of the curve
    val fabMargin = height - radius.times(2) - curveBottomOffset
    val curveHalfWidth = radius * 2 + fabMargin

    // offset of the first control point (top part)
    val topControlX = curveHalfWidth / 2
    val topControlY = 0 + bottomNavOffsetY

    // offset of the second control point (bottom part)
    val bottomControlX = curveHalfWidth / 2
    val bottomControlY = 0


    // first curve
    // set the starting point of the curve (P2)
    val firstCurveStart = Offset(
        x = offsetX - curveHalfWidth,
        y = bottomNavOffsetY
    )
    // set the end point for the first curve (P3)
    val firstCurveEnd = Offset(
        x = offsetX,
        y = height - curveBottomOffset
    )
    // set the first control point (C1)
    val firstCurveControlPoint1 = Offset(
        x = firstCurveStart.x + topControlX,
        y = topControlY
    )
    // set the second control point (C2)
    val firstCurveControlPoint2 = Offset(
        x = firstCurveEnd.x - bottomControlX,
        y = firstCurveEnd.y - bottomControlY
    )

    // second curve
    // end of first curve and start of second curve is the same (P3)
    val secondCurveStart = Offset(firstCurveEnd.x, firstCurveEnd.y)
    // end of the second curve (P4)
    val secondCurveEnd = Offset(
        x = offsetX + curveHalfWidth,
        y = bottomNavOffsetY
    )
    // set the first control point of second curve (C4)
    val secondCurveControlPoint1 = Offset(
        x = secondCurveStart.x + bottomControlX,
        y = secondCurveStart.y - bottomControlY
    )
    // set the second control point (C3)
    val secondCurveControlPoint2 = Offset(
        x = secondCurveEnd.x - topControlX,
        y = topControlY
    )

    // clear any previous path
    path.reset()
    // start from P1 of the BottomNavigationView
    path.moveTo(x = left, y = bottomNavOffsetY)
    // horizontal line from P1 to P2
    path.lineTo(x = firstCurveStart.x, y = firstCurveStart.y)
    // bezier curve with (P2, C1, C2, P3)
    path.cubicTo(
        x1 = firstCurveControlPoint1.x,
        y1 = firstCurveControlPoint1.y,
        x2 = firstCurveControlPoint2.x,
        y2 = firstCurveControlPoint2.y,
        x3 = firstCurveEnd.x,
        y3 = firstCurveEnd.y
    )

    path.quadraticBezierTo(
        x1 = offsetX,
        y1 = bottom - curveBottomOffset,
        x2 = secondCurveStart.x,
        y2 = secondCurveStart.y
    )
    // bezier curve with (P3, C4, C3, P4)
    path.cubicTo(
        x1 = secondCurveControlPoint1.x,
        y1 = secondCurveControlPoint1.y,
        x2 = secondCurveControlPoint2.x,
        y2 = secondCurveControlPoint2.y,
        x3 = secondCurveEnd.x,
        y3 = secondCurveEnd.y
    )
    // line from P4 to P5
    path.lineTo(x = right, y = bottomNavOffsetY)
    // line from P5 to P6
    path.lineTo(x = right, y = bottom)
    // line from P6 to P7
    path.lineTo(x = left, y = bottom)
    // complete the path
    path.close()

    return path
}
