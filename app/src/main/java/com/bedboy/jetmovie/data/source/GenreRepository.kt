package com.bedboy.jetmovie.data.source

import com.bedboy.jetmovie.data.source.local.entity.GenreEntity
import com.bedboy.jetmovie.data.source.remote.RemoteDataSource

class GenreRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    GenreDataSource {

    companion object {
        @Volatile
        private var instance: GenreRepository? = null
        fun getInstance(remoteData: RemoteDataSource): GenreRepository =
            instance ?: synchronized(this) {
                instance ?: GenreRepository(remoteData).apply { instance = this }
            }
    }

    override fun getGenre(): List<GenreEntity> {
        val genreResponse = remoteDataSource.getGenre()
        val genreList = ArrayList<GenreEntity>()

        for (response in genreResponse) {
            val genre = GenreEntity(
                response.id,
                response.name
            )
            genreList.add(genre)
        }
        return genreList
    }

}