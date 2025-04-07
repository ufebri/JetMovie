package com.raylabs.jetmovie.core.domain.usecase.movietv

import androidx.paging.PagingData
import com.raylabs.jetmovie.core.domain.model.MoviesTV
import kotlinx.coroutines.flow.Flow

interface MovieTVUseCase {
    fun getUpComingMovieTV(): Flow<PagingData<MoviesTV>>
    fun getPopularMovieTV(): Flow<PagingData<MoviesTV>>
    fun getTrendingMovieTV(): Flow<PagingData<MoviesTV>>
}