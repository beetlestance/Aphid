package com.beetlestance.aphid.common_compose.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Immutable
class AphidShapes(
    /**
     * Shape used by small components like [Button] or [Snackbar]. Components like
     * [FloatingActionButton], [ExtendedFloatingActionButton] use this shape, but override
     * the corner size to be 50%. [TextField] uses this shape with overriding the bottom corners
     * to zero.
     */
    val small: CornerBasedShape = RoundedCornerShape(4.dp),
    /**
     * Shape used by medium components like [Card] or [AlertDialog].
     */
    val medium: CornerBasedShape = RoundedCornerShape(4.dp),
    /**
     * Shape used by large components like [ModalDrawer] or [ModalBottomSheetLayout].
     */
    val large: CornerBasedShape = RoundedCornerShape(0.dp)
) {

    /**
     * Returns a copy of this Shapes, optionally overriding some of the values.
     */
    fun copy(
        small: CornerBasedShape = this.small,
        medium: CornerBasedShape = this.medium,
        large: CornerBasedShape = this.large
    ): AphidShapes = AphidShapes(
        small = small,
        medium = medium,
        large = large
    )

    fun materialShapes() = Shapes(
        small = small,
        medium = medium,
        large = large
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AphidShapes) return false

        if (small != other.small) return false
        if (medium != other.medium) return false
        if (large != other.large) return false

        return true
    }

    override fun hashCode(): Int {
        var result = small.hashCode()
        result = 31 * result + medium.hashCode()
        result = 31 * result + large.hashCode()
        return result
    }

    override fun toString(): String {
        return "Shapes(small=$small, medium=$medium, large=$large)"
    }
}

internal val LocalAphidShapes = staticCompositionLocalOf { AphidShapes() }