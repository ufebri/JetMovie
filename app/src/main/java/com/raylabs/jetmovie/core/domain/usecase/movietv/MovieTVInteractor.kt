package com.raylabs.jetmovie.core.domain.usecase.movietv

import androidx.paging.PagingData
import com.raylabs.jetmovie.core.domain.model.MoviesTV
import com.raylabs.jetmovie.core.domain.repository.IMovieTVRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieTVInteractor @Inject constructor(private val movieTVRepository: IMovieTVRepository):
    MovieTVUseCase {

    override fun getUpComingMovieTV(): Flow<PagingData<MoviesTV>> = movieTVRepository.getUpComingMovieTV()

    override fun getPopularMovieTV(): Flow<PagingData<MoviesTV>> = movieTVRepository.getPopularMovieTV()

    override fun getTrendingMovieTV(): Flow<PagingData<MoviesTV>> = movieTVRepository.getTrendMovieTV()
}