package com.bw.simple_color_picker.screen.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.bw.simple_color_picker.feature.slider_bar.presentation.composables.LightnessBar
import com.bw.simple_color_picker.feature.slider_bar.presentation.composables.LinearPointer

@Composable
fun LightnessSlider(
    barLength: Dp,
    barWidth: Dp,
    pointerSize: Dp,
    borderSize: Dp,
    elevation: Dp,
    hue: () -> Float,
    saturation: () -> Float,
    onLightnessChange: (saturation: Float) -> Unit
) {
    Box(Modifier.size((barLength + pointerSize), pointerSize)) {
        LightnessBar(
            modifier = Modifier.align(Alignment.Center),
            barLength = barLength,
            barWidth = barWidth,
            hue = hue(),
            saturation = saturation()
        )
        LinearPointer(
            center = true,
            pointerSize = pointerSize,
            barLength = barLength,
            borderSize = borderSize,
            elevation = elevation,
            hue = hue(),
            saturation = saturation(),
            onValueChange = onLightnessChange
        )
    }
}