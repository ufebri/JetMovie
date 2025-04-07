package com.raylabs.jetmovie.ui.detail

import androidx.lifecycle.ViewModel
import com.raylabs.jetmovie.core.domain.usecase.movietv.MovieTVUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(useCase: MovieTVUseCase) : ViewModel() {

//    private val dataID = MutableLiveData<String>()
//
//    fun selectedData(dataID: String) {
//        this.dataID.value = dataID
//    }
//
//    fun getVideos(media_type: String, idData: String): LiveData<Resource<List<VideoEntity>>> =
//        dataRepository.getVideoDetail(media_type, idData)
//
//    var getDetailTV: LiveData<Resource<DataMovieTVEntity>> =
//        dataID.switchMap { mDataID ->
//            dataRepository.getDetailTV(mDataID)
//        }
//
//    var getDetailMovie: LiveData<Resource<DataMovieTVEntity>> =
//        dataID.switchMap { mDataID ->
//            dataRepository.getDetailMovie(mDataID)
//        }
//
//    fun addToWatchList() {
//        val dataResource = getDetailTV.value ?: getDetailMovie.value
//        if (dataResource?.data != null) {
//            val newState = !dataResource.data.isFavorite
//            dataRepository.setWatchList(dataResource.data, newState)
//        }
//    }
//
//    fun addToRemind(title: String, message: String, triggerTimeMillis: Long) =
//        dataRepository.scheduleReminder(title, message, triggerTimeMillis)
}