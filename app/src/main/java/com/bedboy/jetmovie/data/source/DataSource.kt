package com.bedboy.jetmovie.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.data.source.local.entity.GenreEntity
import com.bedboy.jetmovie.data.source.local.entity.VideoEntity
import com.bedboy.jetmovie.vo.Resource
import kotlinx.coroutines.flow.Flow

interface DataSource {

    fun getTrending(): LiveData<Resource<PagedList<DataMovieTVEntity>>>

    fun getPopular(): LiveData<Resource<PagedList<DataMovieTVEntity>>>

    fun getVideoDetail(mediaType: String, id: String): LiveData<Resource<List<VideoEntity>>>

    fun getGenre(mediaType: String): LiveData<Resource<List<GenreEntity>>>

    fun getWatchList(): LiveData<PagedList<DataMovieTVEntity>>

    fun setWatchList(data: DataMovieTVEntity, state: Boolean)

    fun getDetailTV(id: String): LiveData<Resource<DataMovieTVEntity>>

    fun getDetailMovie(id: String): LiveData<Resource<DataMovieTVEntity>>

    fun getAllUpcoming(): LiveData<Resource<PagedList<DataMovieTVEntity>>>

    fun getMovieByKeyword(keyword: String): LiveData<Resource<PagedList<DataMovieTVEntity>>>

    fun getThemeSetting(): Flow<Boolean>

    suspend fun saveThemeSetting(isDarkModeActive: Boolean)
}