package com.raylabs.jetmovie.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raylabs.jetmovie.core.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.core.data.source.local.entity.GenreEntity
import com.raylabs.jetmovie.core.data.source.local.entity.RemoteKeys
import com.raylabs.jetmovie.core.data.source.local.entity.VideoEntity

@Database(
    entities = [DataMovieTVEntity::class, GenreEntity::class, VideoEntity::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class JetMovieDatabase : RoomDatabase() {

    abstract fun jetMovieDao(): JetMovieDao
}