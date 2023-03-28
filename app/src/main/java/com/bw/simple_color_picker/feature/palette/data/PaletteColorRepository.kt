package com.bw.simple_color_picker.feature.palette.data

import com.bw.simple_color_picker.feature.palette.domain.PaletteColorEntity

class PaletteColorRepository(private val paletteColorDao: PaletteColorDao) {
    fun insertPaletteColor(paletteColorEntity: PaletteColorEntity) {
        paletteColorDao.insertPaletteColor(paletteColorEntity)
    }

    fun loadAllPaletteColors(): List<PaletteColorEntity> {
        return paletteColorDao.loadAllPaletteColors()
    }
}