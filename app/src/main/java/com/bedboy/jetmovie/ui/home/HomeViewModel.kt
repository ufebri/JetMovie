package com.bedboy.jetmovie.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bedboy.jetmovie.data.source.DataRepository
import com.bedboy.jetmovie.data.source.remote.response.ResultsGenre
import com.bedboy.jetmovie.data.source.remote.response.ResultsItem
import com.bedboy.jetmovie.ui.home.HomeActivity.Companion.MEDIATYPE

class HomeViewModel(dataRepository: DataRepository) : ViewModel() {

    val trending: LiveData<List<ResultsItem>> = dataRepository.getTrending()
    val popular: LiveData<List<ResultsItem>> = dataRepository.getPopular()
    val genre: LiveData<List<ResultsGenre>> = dataRepository.getGenre(MEDIATYPE)


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

}