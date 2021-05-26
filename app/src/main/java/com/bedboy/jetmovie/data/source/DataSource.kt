package com.bedboy.jetmovie.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.data.source.local.entity.GenreEntity
import com.bedboy.jetmovie.data.source.local.entity.VideoEntity
import com.bedboy.jetmovie.vo.Resource

interface DataSource {

    fun getTrending(): LiveData<Resource<PagedList<DataMovieTVEntity>>>

    fun getPopular(): LiveData<Resource<PagedList<DataMovieTVEntity>>>

    fun getVideoDetail(media_type: String, id: String): LiveData<Resource<List<VideoEntity>>>

    fun getGenre(media_type: String): LiveData<Resource<List<GenreEntity>>>

    fun getWatchList(): LiveData<PagedList<DataMovieTVEntity>>

    fun setWatchList(data: DataMovieTVEntity, state: Boolean)

    fun getDetailTV(id: String): LiveData<Resource<DataMovieTVEntity>>

    fun getDetailMovie(id: String): LiveData<Resource<DataMovieTVEntity>>
}