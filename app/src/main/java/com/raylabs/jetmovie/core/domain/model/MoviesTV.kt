package com.raylabs.jetmovie.core.domain.model

data class MoviesTV(
    val id: Int,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val rating: String,
    val ratingBar: Float,
    val genre: String,
    val releaseDate: String
)