package com.raylabs.jetmovie.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.raylabs.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.data.source.local.entity.GenreEntity
import com.raylabs.jetmovie.data.source.local.entity.VideoEntity

@Database(
    entities = [DataMovieTVEntity::class, GenreEntity::class, VideoEntity::class],
    version = 2,
    exportSchema = false
)

abstract class JetMovieDatabase : RoomDatabase() {

    abstract fun jetMovieDao(): JetMovieDao

    companion object {
        @Volatile
        private var INSTANCE: JetMovieDatabase? = null

        fun getInstance(context: Context): JetMovieDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    JetMovieDatabase::class.java, "JetMovie.db"
                ).fallbackToDestructiveMigration().build()
            }
    }
}