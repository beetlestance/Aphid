package com.beetlestance.aphid.common_compose.utils

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density

class CurveCut(
    private val rect: Rect,
    private val offsetX: Float,
    private val curveBottomOffset: Float,
    private val radius: Float
) : Shape {

    override fun createOutline(size: Size, density: Density): Outline {
        val path = rect.computeCurve(offsetX, curveBottomOffset, radius)
        return Outline.Generic(path)
    }
}
