package com.bedboy.jetmovie.data.source

import androidx.lifecycle.LiveData
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.data.source.local.entity.GenreEntity
import com.bedboy.jetmovie.data.source.local.entity.VideoEntity

interface DataSource {

    fun getTrending(): LiveData<List<DataMovieTVEntity>>

    fun getPopular(): LiveData<List<DataMovieTVEntity>>

    fun getVideoDetail(media_type: String, id: String): LiveData<List<VideoEntity>>

    fun getGenre(media_type: String): LiveData<List<GenreEntity>>
}