package com.raylabs.jetmovie.core.di

import android.content.Context
import androidx.room.Room
import com.raylabs.jetmovie.BuildConfig
import com.raylabs.jetmovie.core.data.source.local.room.JetMovieDao
import com.raylabs.jetmovie.core.data.source.local.room.JetMovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): JetMovieDatabase {
        val passphrase = SQLiteDatabase.getBytes(BuildConfig.DB_PASS.toCharArray())
        val factory = if (BuildConfig.DEBUG) null else SupportFactory(passphrase)

        return Room.databaseBuilder(context, JetMovieDatabase::class.java, BuildConfig.DB_NAME)
            .openHelperFactory(factory)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMovieDao(database: JetMovieDatabase): JetMovieDao = database.jetMovieDao()
}