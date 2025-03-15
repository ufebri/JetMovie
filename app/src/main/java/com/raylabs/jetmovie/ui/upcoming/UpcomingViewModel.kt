package com.raylabs.jetmovie.ui.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.raylabs.jetmovie.data.source.DataRepository
import com.raylabs.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.raylabs.jetmovie.vo.Resource

class UpcomingViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun getAllUpcoming(): LiveData<Resource<PagedList<DataMovieTVEntity>>> = dataRepository.getAllUpcoming()
}