package com.bw.simple_color_picker.common

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import kotlin.math.*

object CoordinateConvertor {
    private var rad: Float = 0f
    var currentDegree: Float = 0f
        get() {
            val deg = rad * (180 / PI)
            return (if (deg >= 0) deg else (360f + deg)).toFloat()
        }
        private set

    fun convertToCartesianCoordinate(radius: Float, point: Offset): IntOffset {
        rad = atan2(point.y, point.x)
        val x = radius * cos(rad)
        val y = radius * sin(rad)
        return IntOffset((x + radius).roundToInt(), (y + radius).roundToInt())
    }
}