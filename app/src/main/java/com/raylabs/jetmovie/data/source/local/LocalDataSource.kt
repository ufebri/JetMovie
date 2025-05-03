package com.raylabs.jetmovie.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.raylabs.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.data.source.local.entity.GenreEntity
import com.raylabs.jetmovie.data.source.local.entity.VideoEntity
import com.raylabs.jetmovie.data.source.local.room.JetMovieDao

class LocalDataSource(private val mJetMovieDao: JetMovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(jetMovieDao: JetMovieDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(jetMovieDao)
            }
            return INSTANCE as LocalDataSource
        }
    }


    fun getAllMovie(dataFrom: String): DataSource.Factory<Int, DataMovieTVEntity> = mJetMovieDao.getAllMovie(dataFrom)

    fun getMovieByKeyword(keyword: String) = mJetMovieDao.getMovieByKeyword(keyword)

    fun insertTrending(trending: List<DataMovieTVEntity>) = mJetMovieDao.insertTrending(trending)


    //Popular Operations
    fun getPopular(): DataSource.Factory<Int, DataMovieTVEntity> = mJetMovieDao.getPopular()

    fun insertPopular(popular: List<DataMovieTVEntity>) = mJetMovieDao.insertPopular(popular)


    //Genre Operations
    fun getGenre(): LiveData<List<GenreEntity>> = mJetMovieDao.getGenre()

    fun getGenres(): List<GenreEntity> = mJetMovieDao.getGenres()

    fun insertGenre(genre: List<GenreEntity>) = mJetMovieDao.insertGenre(genre)


    //Video Operations
    fun getVideo(id: String): LiveData<List<VideoEntity>> = mJetMovieDao.getVideo(id)

    fun insertVideo(video: List<VideoEntity>) = mJetMovieDao.insertVideo(video)

    //Detail Operations
    fun getDetail(id: String): LiveData<DataMovieTVEntity> = mJetMovieDao.getDetailByID(id)

    fun updateDetail(detail: DataMovieTVEntity, newState: Boolean) {
        detail.isFavorite = newState
        mJetMovieDao.updateDetailByID(detail)
    }


    //WatchList Operations
    fun getWatchList(): DataSource.Factory<Int, DataMovieTVEntity> = mJetMovieDao.getWatchList()

    fun setWatchList(watchList: DataMovieTVEntity, newState: Boolean) {
        watchList.isFavorite = newState
        mJetMovieDao.updateDetailByID(watchList)
    }
}