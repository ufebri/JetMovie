package com.bedboy.jetmovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bedboy.jetmovie.data.source.DataRepository
import com.bedboy.jetmovie.data.source.remote.response.ResultsVideos
import com.bedboy.jetmovie.ui.detail.DetailActivity.Companion.dataID
import com.bedboy.jetmovie.ui.detail.DetailActivity.Companion.mediaType

class DetailViewModel(dataRepository: DataRepository) : ViewModel() {

    val videos: LiveData<List<ResultsVideos>> = dataRepository.getVideoDetail(mediaType, dataID)

}