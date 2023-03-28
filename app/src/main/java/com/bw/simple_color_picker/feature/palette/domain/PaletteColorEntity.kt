package com.bw.simple_color_picker.feature.palette.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "paletteColor")
data class PaletteColorEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "color_argb") val colorArgb: Int
)