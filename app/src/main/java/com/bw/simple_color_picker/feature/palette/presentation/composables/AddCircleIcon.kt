package com.bw.simple_color_picker.feature.palette.presentation.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AddCircleIcon(
    modifier: Modifier = Modifier,
    width: Dp,
    color: Color = Color.Black
) {
    BoxWithConstraints(modifier) {
        val constraintWidth = minWidth
        val constraintHeight = minHeight

        Canvas(
            Modifier
                .fillMaxSize()
                .padding(width / 2)) {
            drawCircle(
                color = color,
                style = Stroke(width = width.toPx())
            )
            drawLine(
                color = color,
                start = Offset(constraintWidth.toPx() / 2 - width.toPx() / 2, 0f + width.toPx() * 2),
                end = Offset(constraintWidth.toPx() / 2 - width.toPx() / 2, constraintHeight.toPx() - width.toPx() * 3),
                strokeWidth = width.toPx(),
                cap = StrokeCap.Round
            )
            drawLine(
                color = color,
                start = Offset(0f + width.toPx() * 2, constraintWidth.toPx() / 2 - width.toPx() / 2),
                end = Offset(constraintHeight.toPx() - width.toPx() * 3, constraintWidth.toPx() / 2 - width.toPx() / 2),
                strokeWidth = width.toPx(),
                cap = StrokeCap.Round
            )
        }
    }
}

@Preview
@Composable
fun AddCircleIconPreview() {
    Box(modifier = Modifier.wrapContentSize().background(Color.White)) {
        AddCircleIcon(Modifier.size(100.dp), width = 4.dp, color = Color(0xFFD2D2D2))
    }
}