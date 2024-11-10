package id.daydream.jetmovie.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import id.daydream.jetmovie.data.source.DataRepository
import id.daydream.jetmovie.data.source.local.entity.DataMovieTVEntity

class WatchListViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun getWatchList(): LiveData<PagedList<DataMovieTVEntity>> = dataRepository.getWatchList()

    fun addToWatchList(dataMovieTVEntity: DataMovieTVEntity) {
        val newState = !dataMovieTVEntity.isFavorite
        dataRepository.setWatchList(dataMovieTVEntity, newState)
    }
}