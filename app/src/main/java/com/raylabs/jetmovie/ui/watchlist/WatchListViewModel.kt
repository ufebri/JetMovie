package com.raylabs.jetmovie.ui.watchlist

import androidx.lifecycle.ViewModel
import com.raylabs.jetmovie.core.domain.usecase.movietv.MovieTVUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(useCase: MovieTVUseCase) : ViewModel() {

//    fun getWatchList(): LiveData<PagedList<DataMovieTVEntity>> = dataRepository.getWatchList()
//
//    fun addToWatchList(dataMovieTVEntity: DataMovieTVEntity) {
//        val newState = !dataMovieTVEntity.isFavorite
//        dataRepository.setWatchList(dataMovieTVEntity, newState)
//    }
}