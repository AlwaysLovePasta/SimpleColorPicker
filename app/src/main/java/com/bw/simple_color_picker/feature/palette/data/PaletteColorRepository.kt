package com.bw.simple_color_picker.feature.palette.data

import com.bw.simple_color_picker.feature.palette.domain.PaletteColorEntity
import javax.inject.Inject

class PaletteColorRepository @Inject constructor(private val paletteColorDao: PaletteColorDao) {
    fun insertPaletteColor(paletteColorEntity: PaletteColorEntity) {
        paletteColorDao.insertPaletteColor(paletteColorEntity)
    }

    fun loadAllPaletteColors(): List<PaletteColorEntity> {
        return paletteColorDao.loadAllPaletteColors()
    }
}