package com.bw.simple_color_picker.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bw.simple_color_picker.feature.palette.presentation.composables.PaletteColorGrids
import com.bw.simple_color_picker.feature.palette.presentation.vm.PaletteColorVM
import com.bw.simple_color_picker.screen.composables.ColorPickerGroup
import com.bw.simple_color_picker.screen.composables.HexTextBox
import com.bw.simple_color_picker.ui.theme.SimpleColorPickerTheme

@Composable
fun ColorPickerScreen(
    paletteColorVM: PaletteColorVM = hiltViewModel()
) {
    Surface(Modifier.fillMaxSize()) {
        var hexColor by remember { mutableStateOf("#FFFFFF") }
        val colors = paletteColorVM.paletteColors.collectAsState().value
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            ColorPickerGroup(
                modifier = Modifier.size(240.dp, 360.dp),
                onColorChange = {
                    hexColor = String.format("#%06X", 0xFFFFFF and it.toArgb())
                    paletteColorVM.updatePickedColor(it)
                }
            )
            HexTextBox(
                modifier = Modifier.size(180.dp, 48.dp),
                textSize = 22.sp,
                iconSize = 22.dp,
                hexText = { hexColor }
            )
            Divider(modifier = Modifier.padding(horizontal = 12.dp))
            PaletteColorGrids(
                itemSize = 40.dp,
                verticalPadding = 18.dp,
                items = { colors },
                onColorAdd = paletteColorVM::addPaletteColor
            )
        }
    }
}

@Preview(device = Devices.PIXEL_2)
@Composable
fun ColorPickerScreenPreview() {
    SimpleColorPickerTheme {
        ColorPickerScreen()
    }
}