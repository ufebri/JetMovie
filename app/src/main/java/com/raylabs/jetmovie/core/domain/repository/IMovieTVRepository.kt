package com.raylabs.jetmovie.core.domain.repository

import androidx.paging.PagingData
import com.raylabs.jetmovie.core.domain.model.MoviesTV
import kotlinx.coroutines.flow.Flow

interface IMovieTVRepository {

    fun getUpComingMovieTV(): Flow<PagingData<MoviesTV>>

    fun getPopularMovieTV(): Flow<PagingData<MoviesTV>>

    fun getTrendMovieTV(): Flow<PagingData<MoviesTV>>
}