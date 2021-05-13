package com.bedboy.jetmovie.data.source.remote

import com.bedboy.jetmovie.data.source.remote.response.GenreResponse
import com.bedboy.jetmovie.utils.JSONHelper

class RemoteDataSource private constructor(private val jsonHelper: JSONHelper) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JSONHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getGenre(): List<GenreResponse> = jsonHelper.loadGenre()
}