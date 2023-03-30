package com.bw.simple_color_picker.feature.hue_picker.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bw.simple_color_picker.common.CoordinateConvertor

@Composable
fun CircularPointer(
    modifier: Modifier = Modifier,
    ringSize: Dp,
    pointerSize: Dp,
    borderSize: Dp? = null,
    onDegreeChange: (degree: Float) -> Unit
) {
    val diameter = with(LocalDensity.current) { ringSize.toPx() }
    val intrinsicSize = with(LocalDensity.current) { pointerSize.toPx() }

    var touchPoint by remember { mutableStateOf(Offset.Zero) }
    var color by remember { mutableStateOf(Color.Red) }
    val position by remember {
        derivedStateOf {
            CoordinateConvertor.convertToCartesianCoordinate(
                radius = diameter / 2 - intrinsicSize / 2,
                point = touchPoint
            )
        }
    }

    Box(
        modifier = modifier
            .offset {
                color = Color.hsl(360f - CoordinateConvertor.currentDegree, 1f, 0.5f)
                position
            }
            .size(pointerSize)
            .shadow(8.dp, CircleShape)
            .clip(CircleShape)
            .background(Color.White)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    touchPoint += dragAmount
                }
            },
        contentAlignment = Alignment.Center
    ) {
        onDegreeChange(360f - CoordinateConvertor.currentDegree)

        if (borderSize != null) {
            Box(
                Modifier
                    .size(pointerSize - (borderSize * 2))
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}

@Preview
@Composable
fun CircularPointerPreview() {
    Box(Modifier.size(100.dp)) {
        CircularPointer(
            ringSize = 100.dp,
            pointerSize = 16.dp,
            borderSize = 2.dp
        ) {}
    }
}