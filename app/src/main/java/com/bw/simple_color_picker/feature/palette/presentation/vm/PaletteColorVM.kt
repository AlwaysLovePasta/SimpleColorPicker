package com.bw.simple_color_picker.feature.palette.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bw.simple_color_picker.feature.palette.data.PaletteColorRepository
import com.bw.simple_color_picker.feature.palette.domain.PaletteColorEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PaletteColorVM(
    private val repository: PaletteColorRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {

    private val _paletteColors = MutableStateFlow(mutableListOf<PaletteColorEntity>())
    val paletteColors: StateFlow<List<PaletteColorEntity>>
        get() = _paletteColors.asStateFlow()

    init {
        updateCurrentPaletteColors()
    }

    fun addPaletteColor(paletteColor: PaletteColorEntity) {
        viewModelScope.launch(dispatcher) {
            repository.insertPaletteColor(paletteColor)
        }
        updateCurrentPaletteColors()
    }

    private fun updateCurrentPaletteColors() {
        viewModelScope.launch(dispatcher) {
            _paletteColors.update { repository.loadAllPaletteColors().toMutableList() }
        }
    }
}