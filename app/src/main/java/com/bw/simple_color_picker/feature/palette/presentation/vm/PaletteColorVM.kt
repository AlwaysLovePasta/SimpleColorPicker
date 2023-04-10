package com.bw.simple_color_picker.feature.palette.presentation.vm

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bw.simple_color_picker.feature.palette.data.PaletteColorRepository
import com.bw.simple_color_picker.feature.palette.domain.PaletteColorEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaletteColorVM @Inject constructor(
    private val repository: PaletteColorRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _pickedColor = MutableStateFlow(Color.Black)
    private val _paletteColors = MutableStateFlow(mutableListOf<PaletteColorEntity>())
    val paletteColors: StateFlow<List<PaletteColorEntity>>
        get() = _paletteColors.asStateFlow()

    init {
        updateCurrentPaletteColors()
    }

    fun updatePickedColor(color: Color) = _pickedColor.update { color }

    fun addPaletteColor() {
        viewModelScope.launch(dispatcher) {
            repository.insertPaletteColor(
                PaletteColorEntity(colorArgb = _pickedColor.value.toArgb())
            )
        }
        updateCurrentPaletteColors()
    }

    private fun updateCurrentPaletteColors() {
        viewModelScope.launch(dispatcher) {
            _paletteColors.update { repository.loadAllPaletteColors().toMutableList() }
        }
    }
}