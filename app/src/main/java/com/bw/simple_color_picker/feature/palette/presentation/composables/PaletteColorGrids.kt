package com.bw.simple_color_picker.feature.palette.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bw.simple_color_picker.feature.palette.domain.PaletteColorEntity

@Composable
fun PaletteColorGrids(
    modifier: Modifier = Modifier,
    itemSize: Dp,
    verticalPadding: Dp = 0.dp,
    horizontalPadding: Dp = 0.dp,
    items: () -> List<PaletteColorEntity>,
    onColorAdd: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(6),
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(verticalPadding),
        horizontalArrangement = Arrangement.spacedBy(horizontalPadding)
    ) {
        item {
            Box(
                Modifier
                    .wrapContentSize()
                    .size(itemSize)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable(onClick = onColorAdd)
            )
        }

        items(items = items()) { paletteColor ->
            Box(
                Modifier
                    .wrapContentSize()
                    .size(itemSize)
                    .clip(CircleShape)
                    .background(Color(paletteColor.colorArgb))
            )
        }
    }
}

@Preview
@Composable
fun PaletteColorGridsPreview() {
    val items = listOf(
        PaletteColorEntity(1, Color.Black.toArgb()),
        PaletteColorEntity(2, Color.Red.toArgb()),
        PaletteColorEntity(3, Color.Green.toArgb()),
        PaletteColorEntity(4, Color.Blue.toArgb()),
        PaletteColorEntity(5, Color.Black.toArgb()),
        PaletteColorEntity(6, Color.Red.toArgb()),
        PaletteColorEntity(7, Color.Green.toArgb()),
        PaletteColorEntity(8, Color.Blue.toArgb()),
        PaletteColorEntity(9, Color.Black.toArgb()),
        PaletteColorEntity(10, Color.Red.toArgb()),
        PaletteColorEntity(11, Color.Green.toArgb()),
    )
    Box(Modifier.wrapContentWidth().height(36.dp)) {
        PaletteColorGrids(
            itemSize = 18.dp,
            items = { items },
            onColorAdd = {}
        )
    }
}