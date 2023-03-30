package com.bw.simple_color_picker.feature.hue_picker.presentation.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.abs

@Composable
fun HueRing(
    modifier: Modifier = Modifier,
    ringSize: Dp,
    ringWidth: Dp
) {
    Canvas(
        modifier = modifier.padding(ringWidth / 2).size(ringSize)
    ) {
        for (azimuth in 0 downTo -359) {
            drawArc(
                color = Color.hsl(abs(azimuth.toFloat()), 1f, 0.5f),
                startAngle = azimuth.toFloat(),
                sweepAngle = -1f,
                useCenter = false,
                style = Stroke(width = ringWidth.toPx())
            )
        }
    }
}

@Preview
@Composable
fun HueRingPreview() {
    Box(modifier = Modifier.size(300.dp)) {
        HueRing(modifier = Modifier.padding(7.dp), ringSize = 300.dp, ringWidth = 18.dp)
        CircularPointer(
            ringSize = 300.dp,
            pointerSize = 32.dp,
            borderSize = 4.dp
        ) {}
    }
}