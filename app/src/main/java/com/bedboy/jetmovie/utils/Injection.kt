package com.bedboy.jetmovie.utils

import com.bedboy.jetmovie.data.source.DataRepository
import com.bedboy.jetmovie.data.source.remote.RemoteDataSource
import com.bedboy.jetmovie.network.ApiConfig

object Injection {
    fun provideRepository(): DataRepository {

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig())

        return DataRepository.getInstance(remoteDataSource)
    }
}