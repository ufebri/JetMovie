package com.bedboy.jetmovie.data.source

import androidx.lifecycle.LiveData
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.data.source.local.entity.GenreEntity
import com.bedboy.jetmovie.data.source.local.entity.VideoEntity
import com.bedboy.jetmovie.vo.Resource

interface DataSource {

    fun getTrending(): LiveData<Resource<List<DataMovieTVEntity>>>

    fun getPopular(): LiveData<Resource<List<DataMovieTVEntity>>>

    fun getVideoDetail(media_type: String, id: String): LiveData<Resource<List<VideoEntity>>>

    fun getGenre(media_type: String): LiveData<Resource<List<GenreEntity>>>

    fun getWatchList(): LiveData<List<DataMovieTVEntity>>

    fun setWatchList(data: DataMovieTVEntity, state: Boolean)

    fun getDetailTV(id: String): LiveData<Resource<DataMovieTVEntity>>

    fun getDetailMovie(id: String): LiveData<Resource<DataMovieTVEntity>>
}