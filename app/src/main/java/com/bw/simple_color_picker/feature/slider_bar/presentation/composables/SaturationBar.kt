package com.bw.simple_color_picker.feature.slider_bar.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SaturationBar(
    modifier: Modifier = Modifier,
    barLength: Dp,
    barWidth: Dp,
    hue: Float,
    lightness: Float
) {
    val colors = remember { mutableStateListOf<Color>() }
    for (s in 0..100) {
        val saturation = s / 100f
        colors.add(Color.hsl(hue, saturation, lightness))
    }

    Box(
        modifier
            .size(barLength, barWidth)
            .clip(CircleShape)
            .background(brush = Brush.horizontalGradient(colors))
    )
}

@Preview
@Composable
fun SaturationBarPreview() {
    Box(
        Modifier.size((120 + 12).dp, 12.dp)
    ) {
        SaturationBar(
            modifier = Modifier.align(Alignment.Center),
            barLength = 120.dp,
            barWidth = 8.dp,
            hue = 0f,
            lightness = 0.5f
        )
        LinearPointer(
            pointerSize = 12.dp,
            borderSize = 2.dp,
            barLength = 120.dp,
            hue = 0f,
            lightness = 0.5f,
            elevation = 8.dp
        ) {}
    }
}