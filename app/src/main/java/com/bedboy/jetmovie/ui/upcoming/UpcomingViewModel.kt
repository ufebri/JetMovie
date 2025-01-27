package com.bedboy.jetmovie.ui.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.bedboy.jetmovie.data.source.DataRepository
import com.bedboy.jetmovie.data.source.local.entity.DataMovieTVEntity
import com.bedboy.jetmovie.vo.Resource

class UpcomingViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun getAllUpcoming(): LiveData<Resource<PagedList<DataMovieTVEntity>>> = dataRepository.getAllUpcoming()
}