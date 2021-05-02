package com.bedboy.jetmovie.ui.home

import androidx.lifecycle.ViewModel
import com.bedboy.jetmovie.data.DataEntity
import com.bedboy.jetmovie.data.FeaturedEntity
import com.bedboy.jetmovie.utils.DataDummy

class HomeViewModel : ViewModel() {

    fun getMovies(): List<DataEntity> = DataDummy.generateMovie()

    fun getTVShow(): List<FeaturedEntity> = DataDummy.generateTVShow()
}