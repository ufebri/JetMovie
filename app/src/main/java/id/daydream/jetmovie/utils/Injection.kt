package id.daydream.jetmovie.utils

import android.content.Context
import id.daydream.jetmovie.data.source.DataRepository
import id.daydream.jetmovie.data.source.local.LocalDataSource
import id.daydream.jetmovie.data.source.local.room.JetMovieDatabase
import id.daydream.jetmovie.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): DataRepository {
        val database = JetMovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.jetMovieDao())
        val appExecutors = AppExecutors()
        return DataRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}