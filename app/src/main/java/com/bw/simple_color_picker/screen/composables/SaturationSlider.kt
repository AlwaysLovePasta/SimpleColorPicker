package com.bw.simple_color_picker.screen.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.bw.simple_color_picker.feature.slider_bar.presentation.composables.LinearPointer
import com.bw.simple_color_picker.feature.slider_bar.presentation.composables.SaturationBar

@Composable
fun SaturationSlider(
    barLength: Dp,
    barWidth: Dp,
    pointerSize: Dp,
    borderSize: Dp,
    elevation: Dp,
    hue: () -> Float,
    lightness: () -> Float,
    onSaturationChange: (saturation: Float) -> Unit
) {
    Box(Modifier.size((barLength + pointerSize), pointerSize)) {
        SaturationBar(
            modifier = Modifier.align(Alignment.Center),
            barLength = barLength,
            barWidth = barWidth,
            hue = hue(),
            lightness = lightness()
        )
        LinearPointer(
            pointerSize = pointerSize,
            barLength = barLength,
            borderSize = borderSize,
            elevation = elevation,
            hue = hue(),
            lightness = lightness(),
            onValueChange = onSaturationChange
        )
    }
}