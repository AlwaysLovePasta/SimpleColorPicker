package com.bw.simple_color_picker.screen.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.bw.simple_color_picker.feature.hue_picker.presentation.composables.CircularPointer
import com.bw.simple_color_picker.feature.hue_picker.presentation.composables.HueRing

@Composable
fun HuePicker(
    ringSize: Dp,
    ringWidth: Dp,
    pointerSize: Dp,
    borderSize: Dp,
    elevation: Dp,
    onHueChange: (hue: Float) -> Unit
) {
    Box(Modifier.size(ringSize)) {
        HueRing(
            modifier = Modifier.padding((pointerSize - ringWidth) / 2),
            ringSize = ringSize,
            ringWidth = ringWidth
        )
        CircularPointer(
            ringSize = ringSize,
            pointerSize = pointerSize,
            borderSize = borderSize,
            elevation = elevation,
            onDegreeChange = onHueChange
        )
    }
}