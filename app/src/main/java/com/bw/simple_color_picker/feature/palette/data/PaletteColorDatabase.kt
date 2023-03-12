package com.bw.simple_color_picker.feature.palette.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bw.simple_color_picker.feature.palette.domain.PaletteColorEntity

@Database(entities = [PaletteColorEntity::class], version = 1)
abstract class PaletteColorDatabase: RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: PaletteColorDatabase? = null

        fun getInstance(context: Context): PaletteColorDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PaletteColorDatabase::class.java,
                        "app_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
    abstract fun paletteColorDao(): PaletteColorDao
}
