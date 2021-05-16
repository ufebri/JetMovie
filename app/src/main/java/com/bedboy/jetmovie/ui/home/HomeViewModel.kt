package com.bedboy.jetmovie.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bedboy.jetmovie.data.source.DataRepository
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.data.source.local.entity.GenreEntity
import com.bedboy.jetmovie.ui.home.HomeActivity.Companion.MEDIATYPE

class HomeViewModel(private val dataRepository: DataRepository) : ViewModel() {


    fun trending(): LiveData<List<DataMovieTVEntity>> = dataRepository.getTrending()
    fun popular(): LiveData<List<DataMovieTVEntity>> = dataRepository.getPopular()
    fun genre(): LiveData<List<GenreEntity>> = dataRepository.getGenre(MEDIATYPE)

}