package com.bedboy.jetmovie.ui.detail

import androidx.lifecycle.ViewModel
import com.bedboy.jetmovie.data.DetailDataEntity
import com.bedboy.jetmovie.utils.DataDummy

class DetailViewModel : ViewModel() {

    private lateinit var idMovie: String

    fun setSelectedMovie(id: String) {
        this.idMovie = id
    }

    private fun getDetailMovie(): List<DetailDataEntity> = DataDummy.generateDetailData(idMovie)

    fun getSelectedMovie(): DetailDataEntity {
        lateinit var movie: DetailDataEntity
        val movieEntities = getDetailMovie()
        for (movieEntity in movieEntities) {
            if (movieEntity.id == idMovie) {
                movie = movieEntity
            }
        }
        return movie
    }
}