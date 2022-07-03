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
package com.beetlestance.aphid.common.compose.pager

import androidx.compose.ui.geometry.Size
import kotlin.math.abs

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
        val NONE: PageTransformation = object : PageTransformation {
            override fun transformPage(
                offset: Float,
                size: Size
            ): PageTransformState {
                return PageTransformState()
            }
        }

        val STAIRCASE_TRANSFORM: PageTransformation = object : PageTransformation {
            override fun transformPage(
                offset: Float,
                size: Size
            ): PageTransformState {
                return when {
                    offset < 0f -> {
                        val scaleFactor = (MIN_SCALE + (1 - MIN_SCALE) * (1 - abs(offset)))
                        PageTransformState(
                            alpha = 1 - offset,
                            scaleX = scaleFactor,
                            scaleY = scaleFactor,
                            translationX = size.width * -offset
                        )
                    }
                    else -> {
                        PageTransformState()
                    }
                }
            }
        }

        val DEPTH_TRANSFORM: PageTransformation = object : PageTransformation {
            override fun transformPage(
                offset: Float,
                size: Size
            ): PageTransformState {
                return when {
                    offset != 0f -> {
                        val scaleFactor = (MIN_SCALE + (1 - MIN_SCALE) * (1 - abs(offset)))
                        PageTransformState(
                            alpha = 1 - offset,
                            scaleX = scaleFactor,
                            scaleY = scaleFactor,
                            translationX = size.width.times(-offset)
                        )
                    }
                    else -> PageTransformState()
                }
            }
        }

        val SCALE_TRANSFORM: PageTransformation = object : PageTransformation {
            override fun transformPage(
                offset: Float,
                size: Size
            ): PageTransformState {
                return when {
                    offset != 0f -> {
                        val scaleFactor = (MIN_SCALE + (1 - MIN_SCALE) * (1 - abs(offset)))
                        PageTransformState(
                            scaleX = scaleFactor,
                            scaleY = scaleFactor
                        )
                    }
                    else -> PageTransformState()
                }
            }
        }

        val ZOOM_OUT: PageTransformation = object : PageTransformation {
            override fun transformPage(
                offset: Float,
                size: Size
            ): PageTransformState {
                return when {
                    offset <= 1 && offset >= -1 -> {
                        val scaleFactor = MIN_SCALE_ZOOM.coerceAtLeast(1 - abs(offset))
                        val vertMargin = size.height * (1 - scaleFactor) / 2
                        val horzMargin = size.width * (1 - scaleFactor) / 2
                        val translationX = if (offset < 0) horzMargin + vertMargin / 2 else {
                            // if page is on the right side do not apply any translation
                            -horzMargin - vertMargin / 2
                        }

                        val alpha = (
                            MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE_ZOOM) / (1 - MIN_SCALE_ZOOM)) * (1 - MIN_ALPHA))
                            )
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
