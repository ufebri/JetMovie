package com.bedboy.jetmovie.utils

import android.content.Context
import com.bedboy.jetmovie.data.source.GenreRepository
import com.bedboy.jetmovie.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): GenreRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JSONHelper(context))

        return GenreRepository.getInstance(remoteDataSource)
    }
}