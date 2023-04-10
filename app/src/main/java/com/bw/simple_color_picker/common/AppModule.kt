package com.bw.simple_color_picker.common

import android.content.Context
import androidx.room.Room
import com.bw.simple_color_picker.feature.palette.data.PaletteColorDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        klass = PaletteColorDatabase::class.java,
        name = "app_database"
    ).build()

    @Singleton
    @Provides
    fun providesDao(database: PaletteColorDatabase) = database.paletteColorDao()

    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}