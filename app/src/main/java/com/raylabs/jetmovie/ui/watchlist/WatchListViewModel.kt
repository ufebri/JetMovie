package com.raylabs.jetmovie.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.raylabs.jetmovie.data.source.DataRepository
import com.raylabs.jetmovie.data.source.local.entity.DataMovieTVEntity

class WatchListViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun getWatchList(): LiveData<PagedList<DataMovieTVEntity>> = dataRepository.getWatchList()

    fun addToWatchList(dataMovieTVEntity: DataMovieTVEntity) {
        val newState = !dataMovieTVEntity.isFavorite
        dataRepository.setWatchList(dataMovieTVEntity, newState)
    }
}