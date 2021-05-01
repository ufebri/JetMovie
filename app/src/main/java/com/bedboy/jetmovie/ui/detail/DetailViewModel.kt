package com.bedboy.jetmovie.ui.detail

import androidx.lifecycle.ViewModel
import com.bedboy.jetmovie.data.DetailMovieEntity
import com.bedboy.jetmovie.utils.DataDummy

class DetailViewModel : ViewModel() {

    private lateinit var idMovie: String

    fun setSelectedMovie(id: String) {
        this.idMovie = id
    }

    private fun getDetailMovie(): List<DetailMovieEntity> = DataDummy.generateDetailMovie(idMovie)

    fun getSelectedMovie(): DetailMovieEntity {
        lateinit var movie: DetailMovieEntity
        val movieEntities = getDetailMovie()
        for (movieEntity in movieEntities) {
            if (movieEntity.id == idMovie) {
                movie = movieEntity
            }
        }
        return movie
    }
}