package com.bedboy.jetmovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bedboy.jetmovie.data.source.DataRepository
import com.bedboy.jetmovie.data.source.local.entity.GenreEntity
import com.bedboy.jetmovie.data.source.local.entity.VideoEntity
import com.bedboy.jetmovie.data.source.remote.response.ResultsGenre
import com.bedboy.jetmovie.data.source.remote.response.ResultsVideos

class DetailViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun getvideos(media_type: String, idData: String): LiveData<List<VideoEntity>> =
        dataRepository.getVideoDetail(media_type, idData)

    fun getGenre(media_type: String): LiveData<List<GenreEntity>> = dataRepository.getGenre(media_type)
}