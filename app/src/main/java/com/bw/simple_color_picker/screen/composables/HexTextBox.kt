package com.bw.simple_color_picker.screen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HexTextBox(
    modifier: Modifier = Modifier,
    clipboardManager: ClipboardManager = LocalClipboardManager.current,
    textSize: TextUnit = 12.sp,
    iconSize: Dp = 12.dp,
    hexText: () -> String
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .border((0.4).dp, Color(0xFFDCDADA), MaterialTheme.shapes.small)
            .background(Color(0xFFF6F3F3))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = hexText(), color = Color(0xFF515151), fontSize = textSize)
            Icon(
                modifier = Modifier
                    .size(iconSize)
                    .clickable {
                        clipboardManager.setText(AnnotatedString(hexText().drop(1)))
                    },
                imageVector = Icons.Filled.ContentCopy,
                tint = Color(0xFF515151),
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun HexTextBoxPreview() {
    Box(
        Modifier
            .wrapContentSize()
            .background(Color.White)
    ) {
        HexTextBox(
            modifier = Modifier
                .padding(12.dp)
                .size(100.dp, 24.dp)
        ) { "FFFFFF" }
    }
}