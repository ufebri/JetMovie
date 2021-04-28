package com.bedboy.jetmovie.ui.home

import androidx.lifecycle.ViewModel
import com.bedboy.jetmovie.data.MovieEntity
import com.bedboy.jetmovie.utils.DataDummy

class HomeViewModel : ViewModel() {

    fun getMovies(): List<MovieEntity> = DataDummy.generateMovie()
}