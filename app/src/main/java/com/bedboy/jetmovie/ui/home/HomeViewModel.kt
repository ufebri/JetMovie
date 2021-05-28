package com.bedboy.jetmovie.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bedboy.jetmovie.data.source.DataRepository
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.data.source.local.entity.GenreEntity
import com.bedboy.jetmovie.vo.Resource

class HomeViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun trending(): LiveData<Resource<PagedList<DataMovieTVEntity>>> = dataRepository.getTrending()
    fun popular(): LiveData<Resource<PagedList<DataMovieTVEntity>>> = dataRepository.getPopular()
    fun genre(mediaType: String): LiveData<Resource<List<GenreEntity>>> =
        dataRepository.getGenre(mediaType)

}