package com.bw.simple_color_picker.feature.slider_bar.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun LinearPointer(
    modifier: Modifier = Modifier,
    center: Boolean = false,
    pointerSize: Dp,
    barLength: Dp,
    hue: Float,
    saturation: Float? = null,
    lightness: Float? = null,
    borderSize: Dp? = null,
    elevation: Dp? = null,
    onValueChange: (value: Float) -> Unit
) {
    val maxPosition = with(LocalDensity.current) { barLength.toPx() }
    val correction = with(LocalDensity.current) { barLength.toPx() / 100f }

    var touchPointX by remember {
        mutableStateOf(if (center) maxPosition / 2 else maxPosition)
    }
    val color by remember {
        derivedStateOf {
            onValueChange(touchPointX / correction / 100f)
            if (saturation != null) {
                Color.hsl(hue, saturation, touchPointX / correction / 100f)
            } else if (lightness != null) {
                Color.hsl(hue, touchPointX / correction / 100f, lightness)
            } else {
                Color.Red
            }
        }
    }
    val position by remember {
        derivedStateOf {
            IntOffset(touchPointX.roundToInt(), 0)
        }
    }

    Box(
        modifier = modifier
            .offset { position }
            .size(pointerSize)
            .shadow(elevation ?: 0.dp, CircleShape)
            .clip(CircleShape)
            .background(Color.White)
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta ->
                    val check = touchPointX + delta
                    if (check in 0f..maxPosition) touchPointX += delta
                }
            ),
        contentAlignment = Alignment.Center
    ) {
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
fun LinearPointerPreview() {
    Box(Modifier.width((120 + 12).dp).wrapContentHeight()) {
        LinearPointer(pointerSize = 12.dp, barLength = 120.dp, hue = 0f) {}
    }
}