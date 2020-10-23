package com.beetlestance.aphid.commoncompose

import androidx.compose.ui.geometry.Size

private const val MIN_SCALE = 0.75f

private const val MIN_SCALE_ZOOM = 0.9f
private const val MIN_ALPHA = 0.7f

data class PageTransformState(
    val alpha: Float = 1f,
    val scaleX: Float = 1f,
    val scaleY: Float = 1f,
    val translationX: Float = 0f,
    val translationY: Float = 0f
)

/**
 * Offset meaning:
 * less than 0 : specifies that the page is in left side of the current page
 * equals to 0 : specifies that it is current page
 * greater than 0 : specifies that page is on the right side of the current page
 */
interface PageTransformation {

    fun transformPage(offset: Float, size: Size): PageTransformState

    companion object {
        val NONE = object : PageTransformation {
            override fun transformPage(
                offset: Float,
                size: Size
            ): PageTransformState {
                return PageTransformState()
            }
        }

        val DEPTH_TRANSFORM = object : PageTransformation {
            override fun transformPage(
                offset: Float,
                size: Size
            ): PageTransformState {
                return when {
                    offset == 0f -> PageTransformState()
                    offset < 0f -> {
                        val scaleFactor = (MIN_SCALE + (1 - MIN_SCALE) * (1 - kotlin.math.abs(
                            offset
                        )))
                        PageTransformState(
                            1 - offset, scaleFactor, scaleFactor,
                            size.width * -offset
                        )
                    }
                    else -> {
                        PageTransformState()
                    }
                }
            }
        }

        val ZOOM_OUT = object : PageTransformation {
            override fun transformPage(
                offset: Float,
                size: Size
            ): PageTransformState {
                return when {
                    offset <= 1 && offset >= -1 -> {
                        val scaleFactor = MIN_SCALE_ZOOM.coerceAtLeast(1 - Math.abs(offset))
                        val vertMargin = size.height * (1 - scaleFactor) / 2
                        val horzMargin = size.width * (1 - scaleFactor) / 2
                        val translationX = if (offset < 0) {
                            0f
                        } else {
                            // if page is on the right side do not apply any translation
                            //horzMargin + vertMargin / 2
                            0f
                        }

                        val alpha = (MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE_ZOOM) / (1 - MIN_SCALE_ZOOM)) * (1 - MIN_ALPHA)))
                        PageTransformState(
                            alpha,
                            scaleFactor,
                            scaleFactor,
                            translationX
                        )
                    }
                    else -> PageTransformState(0f, 0f, 0f)
                }
            }
        }
    }
}
