package com.bedboy.jetmovie.utils

import android.content.Context
import com.bedboy.jetmovie.data.source.DataRepository
import com.bedboy.jetmovie.data.source.local.LocalDataSource
import com.bedboy.jetmovie.data.source.local.room.JetMovieDatabase
import com.bedboy.jetmovie.data.source.preferences.SettingPreferences
import com.bedboy.jetmovie.data.source.preferences.dataStore
import com.bedboy.jetmovie.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): DataRepository {
        val database = JetMovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.jetMovieDao())
        val appExecutors = AppExecutors()
        val settingPreferences = SettingPreferences.getInstance(context.dataStore)
        return DataRepository.getInstance(
            remoteDataSource,
            localDataSource,
            appExecutors,
            settingPreferences
        )
    }
}