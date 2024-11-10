package id.daydream.jetmovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import id.daydream.jetmovie.data.source.DataRepository
import id.daydream.jetmovie.data.source.local.entity.DataMovieTVEntity
import id.daydream.jetmovie.data.source.local.entity.VideoEntity
import id.daydream.jetmovie.vo.Resource

class DetailViewModel(private val dataRepository: DataRepository) : ViewModel() {

    private val dataID = MutableLiveData<String>()

    fun selectedData(dataID: String) {
        this.dataID.value = dataID
    }

    fun getVideos(media_type: String, idData: String): LiveData<Resource<List<VideoEntity>>> =
        dataRepository.getVideoDetail(media_type, idData)

    var getDetailTV: LiveData<Resource<DataMovieTVEntity>> =
        Transformations.switchMap(dataID) { mDataID ->
            dataRepository.getDetailTV(mDataID)
        }

    var getDetailMovie: LiveData<Resource<DataMovieTVEntity>> =
        Transformations.switchMap(dataID) { mDataID ->
            dataRepository.getDetailMovie(mDataID)
        }

    fun addToWatchList() {
        val dataResource = getDetailTV.value ?: getDetailMovie.value
        if (dataResource?.data != null) {
            val newState = !dataResource.data.isFavorite
            dataRepository.setWatchList(dataResource.data, newState)
        }
    }
}