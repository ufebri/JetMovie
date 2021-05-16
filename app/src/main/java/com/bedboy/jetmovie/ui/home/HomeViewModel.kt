package com.bedboy.jetmovie.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bedboy.jetmovie.data.source.DataRepository
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.data.source.local.entity.GenreEntity
import com.bedboy.jetmovie.data.source.remote.response.ResultsGenre
import com.bedboy.jetmovie.data.source.remote.response.ResultsItem
import com.bedboy.jetmovie.ui.home.HomeActivity.Companion.MEDIATYPE

class HomeViewModel(dataRepository: DataRepository) : ViewModel() {

    val trending: LiveData<List<DataMovieTVEntity>> = dataRepository.getTrending()
    val popular: LiveData<List<DataMovieTVEntity>> = dataRepository.getPopular()
    val genre: LiveData<List<GenreEntity>> = dataRepository.getGenre(MEDIATYPE)

}