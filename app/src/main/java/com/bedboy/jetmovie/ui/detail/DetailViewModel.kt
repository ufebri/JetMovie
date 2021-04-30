package com.bedboy.jetmovie.ui.detail

import androidx.lifecycle.ViewModel
import com.bedboy.jetmovie.data.MovieEntity
import com.bedboy.jetmovie.utils.DataDummy

class DetailViewModel : ViewModel() {

    fun getMovies(): List<MovieEntity> = DataDummy.generateMovie()
}