package com.raylabs.jetmovie.utils

import android.content.Context
import androidx.work.WorkManager
import com.raylabs.jetmovie.data.repository.SettingsRepository
import com.raylabs.jetmovie.data.source.DataRepository
import com.raylabs.jetmovie.data.source.local.LocalDataSource
import com.raylabs.jetmovie.data.source.local.room.JetMovieDatabase
import com.raylabs.jetmovie.data.source.preferences.SettingPreferences
import com.raylabs.jetmovie.data.source.preferences.dataStore
import com.raylabs.jetmovie.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): DataRepository {
        val database = JetMovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.jetMovieDao())
        val appExecutors = AppExecutors()
        val workManager = WorkManager.getInstance(context)
        return DataRepository.getInstance(
            remoteDataSource,
            localDataSource,
            appExecutors,
            workManager
        )
    }

    fun provideSettingRepository(context: Context) : SettingsRepository {
        val settingPreferences = SettingPreferences.getInstance(context.dataStore)
        return SettingsRepository.getInstance(settingPreferences)
    }
}