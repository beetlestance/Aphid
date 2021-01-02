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
package com.beetlestance.aphid.common_compose.theme.shapes

import androidx.annotation.Px
import androidx.compose.material.BottomAppBar
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.addOutline
import androidx.compose.ui.unit.Density
import com.beetlestance.aphid.common_compose.utils.square
import kotlin.math.sqrt

/**
 * Took BottomAppBarCutoutShape from [BottomAppBar]
 * */
class CutOutShape(
    private val cutOutShape: Shape,
    private val cutOutShapeSize: Size,
    @Px private val cutOutShapeMargin: Float,
    @Px private val cutoutStartOffset: Float,
    @Px private val cutOutEdgeRadius: Float = 0f,
    private val smoothEdge: Boolean = true
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

    private fun Path.addCutoutShape(
        density: Density
    ) {
        // The gap on all sides between the FAB and the cutout
        val cutoutOffset = cutOutShapeMargin

        val cutoutSize = Size(
            width = cutOutShapeSize.width + (cutoutOffset * 2),
            height = cutOutShapeSize.height + (cutoutOffset * 2)
        )

        val cutoutStartX = cutoutStartOffset - cutoutOffset
        val cutoutEndX = cutoutStartX + cutoutSize.width

        val cutoutRadius = cutoutSize.height / 2f
        // Shift the cutout up by half its height, so only the bottom half of the cutout is actually
        // cut into the app bar
        val cutoutStartY = -cutoutRadius

        addOutline(cutOutShape.createOutline(cutoutSize, density))
        translate(Offset(cutoutStartX, cutoutStartY))

        if (smoothEdge) {
            addRoundedEdges(cutoutStartX, cutoutEndX, cutoutRadius, cutOutEdgeRadius, 0f)
        }
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

    private fun calculateCutoutCircleYIntercept(
        cutoutRadius: Float,
        verticalOffset: Float
    ): Float {
        return -sqrt(square(cutoutRadius) - square(verticalOffset))
    }
}
