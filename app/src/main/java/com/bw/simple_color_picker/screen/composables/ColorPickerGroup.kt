package com.bw.simple_color_picker.screen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ColorPickerGroup(
    modifier: Modifier = Modifier,
    onColorChange: (argb: Int) -> Unit
) {
    BoxWithConstraints(modifier = modifier) {
        val minWidth = minWidth
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val pointerSize = 32.dp
            val pointerBorderSize = 4.dp
            val pointerElevation = 8.dp
            val strokeWidth = 18.dp

            var hue by remember { mutableStateOf(0f) }
            var saturation by remember { mutableStateOf(1f) }
            var lightness by remember { mutableStateOf(0.5f) }
            val color by remember {
                derivedStateOf { Color.hsl(hue, saturation, lightness) }
            }

            onColorChange(color.toArgb())

            Box {
                Box(
                    Modifier
                        .align(Alignment.Center)
                        .size(minWidth)
                        .padding(48.dp)
                        .clip(CircleShape)
                        .background(color)
                )
                HuePicker(
                    ringSize = minWidth,
                    ringWidth = strokeWidth,
                    pointerSize = pointerSize,
                    borderSize = pointerBorderSize,
                    elevation = pointerElevation,
                    onHueChange = { hue = it }
                )
            }
            SaturationSlider(
                barLength = minWidth - pointerSize,
                barWidth = strokeWidth,
                pointerSize = pointerSize,
                borderSize = pointerBorderSize,
                elevation = pointerElevation,
                hue = { hue },
                lightness = { lightness },
                onSaturationChange = { saturation = it }
            )
            LightnessSlider(
                barLength = minWidth - pointerSize,
                barWidth = strokeWidth,
                pointerSize = pointerSize,
                borderSize = pointerBorderSize,
                elevation = pointerElevation,
                hue = { hue },
                saturation = { saturation },
                onLightnessChange = { lightness = it }
            )
        }
    }
}

@Preview
@Composable
fun ColorPickerGroupPreview() {
    ColorPickerGroup(onColorChange = {})
}