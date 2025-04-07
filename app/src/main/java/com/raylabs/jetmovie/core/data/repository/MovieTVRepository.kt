package com.raylabs.jetmovie.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.raylabs.jetmovie.core.data.paging.MovieTVLocalPagingSource
import com.raylabs.jetmovie.core.data.paging.MovieTVRemotePagingSource
import com.raylabs.jetmovie.core.data.source.local.room.JetMovieDatabase
import com.raylabs.jetmovie.core.data.source.remote.network.ApiService
import com.raylabs.jetmovie.core.domain.model.MoviesTV
import com.raylabs.jetmovie.core.domain.repository.IMovieTVRepository
import com.raylabs.jetmovie.utils.DataHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieTVRepository @Inject constructor(
    private val database: JetMovieDatabase,
    private val apiService: ApiService
) : IMovieTVRepository {

    private companion object ApiConstants {
        const val ENDPOINT_UPCOMING_MOVIES = "movie/upcoming"
        const val ENDPOINT_TOP_RATED_MOVIES = "movie/top_rated"
        const val ENDPOINT_TRENDING_MOVIES = "trending/all/day"
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getUpComingMovieTV(): Flow<PagingData<MoviesTV>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = MovieTVRemotePagingSource(
                database = database,
                endPoint = ENDPOINT_UPCOMING_MOVIES,
                apiService = apiService,
                sourceData = DataHelper.DataFrom.UPCOMING.value
            ),
            pagingSourceFactory = {
                MovieTVLocalPagingSource(
                    database.jetMovieDao(),
                    DataHelper.DataFrom.UPCOMING.value
                )
            }
        ).flow
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getPopularMovieTV(): Flow<PagingData<MoviesTV>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = MovieTVRemotePagingSource(
                database = database,
                endPoint = ENDPOINT_TOP_RATED_MOVIES,
                apiService = apiService,
                sourceData = DataHelper.DataFrom.POPULAR.value
            ),
            pagingSourceFactory = {
                MovieTVLocalPagingSource(
                    database.jetMovieDao(),
                    DataHelper.DataFrom.POPULAR.value
                )
            }
        ).flow
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getTrendMovieTV(): Flow<PagingData<MoviesTV>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = MovieTVRemotePagingSource(
                database = database,
                endPoint = ENDPOINT_TRENDING_MOVIES,
                apiService = apiService,
                sourceData = DataHelper.DataFrom.TRENDING.value
            ),
            pagingSourceFactory = {
                MovieTVLocalPagingSource(
                    database.jetMovieDao(),
                    DataHelper.DataFrom.TRENDING.value
                )
            }
        ).flow
    }
}