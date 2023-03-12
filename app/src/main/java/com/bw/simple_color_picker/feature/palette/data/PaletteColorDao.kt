package com.bw.simple_color_picker.feature.palette.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bw.simple_color_picker.feature.palette.domain.PaletteColorEntity

@Dao
interface PaletteColorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPaletteColor(paletteColor: PaletteColorEntity)

    @Query("SELECT * FROM paletteColor")
    fun loadAllPaletteColors(): List<PaletteColorEntity>
}