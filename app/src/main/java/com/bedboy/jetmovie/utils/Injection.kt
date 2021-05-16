package com.bedboy.jetmovie.utils

import com.bedboy.jetmovie.data.source.DataRepository
import com.bedboy.jetmovie.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): DataRepository {

        val remoteDataSource = RemoteDataSource.getInstance()

        return DataRepository.getInstance(remoteDataSource)
    }
}