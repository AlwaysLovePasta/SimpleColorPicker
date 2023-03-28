package com.bw.simple_color_picker.feature.palette.data

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.bw.simple_color_picker.feature.palette.domain.PaletteColorEntity
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import java.io.IOException

class PaletteColorDaoTest {
    private lateinit var db: PaletteColorDatabase
    private lateinit var paletteColorDao: PaletteColorDao

    companion object {
        private val TEST_COLOR = Color.Black
        private val TEST_ENTITY = PaletteColorEntity(1, TEST_COLOR.toArgb())
    }

    @Before
    fun setUp() {
         val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, PaletteColorDatabase::class.java).build()
        paletteColorDao = db.paletteColorDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun paletteColorDao_Initialization() {
        val actual = paletteColorDao.loadAllPaletteColors()
        assertEquals(listOf<PaletteColorEntity>(), actual)
    }

    @Test
    @Throws(Exception::class)
    fun paletteColorDao_InsertAndReadForOnce_IsNotEmpty() {
        paletteColorDao.insertPaletteColor(TEST_ENTITY)
        val actual = paletteColorDao.loadAllPaletteColors()
        assertTrue(actual.isNotEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun paletteColorDao_InsertAndReadForOnce_HasRightResult() {
        paletteColorDao.insertPaletteColor(TEST_ENTITY)
        val actual = paletteColorDao.loadAllPaletteColors()
        assertEquals(listOf(TEST_ENTITY), actual)
    }

    @Test
    @Throws(Exception::class)
    fun paletteColorDao_InsertAndReadForTwice_SizeEqualsToTwo() {
        repeat(2) {
            val entity = PaletteColorEntity(it+1, TEST_COLOR.toArgb())
            paletteColorDao.insertPaletteColor(entity)
        }
        val actual = paletteColorDao.loadAllPaletteColors()
        assertEquals(2, actual.size)
    }
}