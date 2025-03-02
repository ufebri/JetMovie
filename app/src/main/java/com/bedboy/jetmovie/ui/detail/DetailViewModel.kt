package com.bedboy.jetmovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.bedboy.jetmovie.data.source.DataRepository
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.data.source.local.entity.VideoEntity
import com.bedboy.jetmovie.vo.Resource

class DetailViewModel(private val dataRepository: DataRepository) : ViewModel() {

    private val dataID = MutableLiveData<String>()

    fun selectedData(dataID: String) {
        this.dataID.value = dataID
    }

    fun getVideos(media_type: String, idData: String): LiveData<Resource<List<VideoEntity>>> =
        dataRepository.getVideoDetail(media_type, idData)

    var getDetailTV: LiveData<Resource<DataMovieTVEntity>> =
        dataID.switchMap { mDataID ->
            dataRepository.getDetailTV(mDataID)
        }

    var getDetailMovie: LiveData<Resource<DataMovieTVEntity>> =
        dataID.switchMap { mDataID ->
            dataRepository.getDetailMovie(mDataID)
        }

    fun addToWatchList() {
        val dataResource = getDetailTV.value ?: getDetailMovie.value
        if (dataResource?.data != null) {
            val newState = !dataResource.data.isFavorite
            dataRepository.setWatchList(dataResource.data, newState)
        }
    }

    fun addToRemind(title: String, message: String, triggerTimeMillis: Long) =
        dataRepository.scheduleReminder(title, message, triggerTimeMillis)
}