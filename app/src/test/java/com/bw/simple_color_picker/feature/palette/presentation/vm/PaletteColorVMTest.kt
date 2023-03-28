package com.bw.simple_color_picker.feature.palette.presentation.vm

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.bw.simple_color_picker.feature.palette.data.PaletteColorRepository
import com.bw.simple_color_picker.feature.palette.domain.PaletteColorEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*

@OptIn(ExperimentalCoroutinesApi::class)
class PaletteColorVMTest {

    @Mock
    private lateinit var repository: PaletteColorRepository
    private lateinit var paletteColorVM: PaletteColorVM
    private val testDispatcher = StandardTestDispatcher()

    companion object {
        private val TEST_PALETTE_COLOR = PaletteColorEntity(1, Color.Black.toArgb())
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mock(PaletteColorRepository::class.java)
        paletteColorVM = PaletteColorVM(repository, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun currentState_Initialization() {
        val actual = paletteColorVM.paletteColors.value
        assertTrue(actual.isEmpty())
    }

    @Test
    fun currentState_AddPaletteColor_IsNotEmpty() = runTest {
        backgroundScope.launch {
            doCallRealMethod().`when`(repository).insertPaletteColor(TEST_PALETTE_COLOR)
            paletteColorVM.addPaletteColor(TEST_PALETTE_COLOR)
            paletteColorVM.paletteColors.collect()
            val actual = paletteColorVM.paletteColors.value
            assertEquals(true, actual.isNotEmpty())
            verify(repository).insertPaletteColor(TEST_PALETTE_COLOR)
        }
    }

    @Test
    fun currentState_AddPaletteColor_HasRightResult() = runTest {
        backgroundScope.launch {
            doCallRealMethod().`when`(repository).insertPaletteColor(TEST_PALETTE_COLOR)
            paletteColorVM.addPaletteColor(TEST_PALETTE_COLOR)
            paletteColorVM.paletteColors.collect()
            val actual = paletteColorVM.paletteColors.value
            assertEquals(listOf(TEST_PALETTE_COLOR), actual)
            verify(repository).insertPaletteColor(TEST_PALETTE_COLOR)
        }
    }
}