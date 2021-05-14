package com.bedboy.jetmovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bedboy.jetmovie.data.source.DataRepository
import com.bedboy.jetmovie.data.source.remote.response.ResultsGenre
import com.bedboy.jetmovie.data.source.remote.response.ResultsVideos

class DetailViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun getvideos(media_type: String, idData: String): LiveData<List<ResultsVideos>> =
        dataRepository.getVideoDetail(media_type, idData)

    fun getGenre(media_type: String): LiveData<List<ResultsGenre>> = dataRepository.getGenre(media_type)
}