package com.raylabs.jetmovie.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.raylabs.jetmovie.data.source.DataRepository
import com.raylabs.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.data.source.local.entity.GenreEntity
import com.raylabs.jetmovie.vo.Resource

class HomeViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun trending(): LiveData<Resource<PagedList<DataMovieTVEntity>>> = dataRepository.getTrending()
    fun popular(): LiveData<Resource<PagedList<DataMovieTVEntity>>> = dataRepository.getPopular()
    fun genre(mediaType: String): LiveData<Resource<List<GenreEntity>>> =
        dataRepository.getGenre(mediaType)
    fun getMovieByKeyword(keyword: String): LiveData<Resource<PagedList<DataMovieTVEntity>>> =
        dataRepository.getMovieByKeyword(keyword)

}