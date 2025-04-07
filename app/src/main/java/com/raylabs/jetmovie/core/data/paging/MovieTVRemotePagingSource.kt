package com.raylabs.jetmovie.core.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.raylabs.jetmovie.core.data.mapper.MovieTVMapper
import com.raylabs.jetmovie.core.data.source.local.entity.RemoteKeys
import com.raylabs.jetmovie.core.data.source.local.room.JetMovieDatabase
import com.raylabs.jetmovie.core.data.source.remote.network.ApiService
import com.raylabs.jetmovie.core.domain.model.MoviesTV
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieTVRemotePagingSource @Inject constructor(
    private val database: JetMovieDatabase,
    private val endPoint: String,
    private val sourceData: String,
    private val apiService: ApiService
) : RemoteMediator<Int, MoviesTV>() {

    private val dao = database.jetMovieDao()

    override suspend fun initialize(): InitializeAction {
        val remoteKey = database.withTransaction {
            dao.remoteKeysByMovieId(sourceData)
        } ?: return InitializeAction.LAUNCH_INITIAL_REFRESH

        val cacheTimeout = TimeUnit.HOURS.convert(1, TimeUnit.MILLISECONDS)

        return if ((System.currentTimeMillis() - remoteKey.lastUpdated) >= cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, MoviesTV>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                1
            }

            LoadType.PREPEND -> {
                return MediatorResult.Success(true)
            }

            LoadType.APPEND -> {
                val remoteKey = database.withTransaction {
                    dao.remoteKeysByMovieId(sourceData)
                } ?: return MediatorResult.Success(true)

                if (remoteKey.nextKey == null) {
                    return MediatorResult.Success(true)
                }

                remoteKey.nextKey
            }
        }


        try {
            val responseData = apiService.fetchMovies(endPoint, page = page)
            if (responseData.isSuccessful) {
                val body = responseData.body()?.results ?: emptyList()
                val mData = MovieTVMapper.mapResponseToEntity(body, sourceData)

                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        dao.clearMoviesTV()
                    }

                    val nextPage = if (body.isEmpty()) {
                        null
                    } else {
                        page + 1
                    }


                    val keys = mData.map {
                        RemoteKeys(
                            keyOfContent = sourceData,
                            nextKey = nextPage,
                            lastUpdated = System.currentTimeMillis()
                        )
                    }
                    dao.insertAll(keys)
                    dao.insertMoviesTV(mData)
                }
                return MediatorResult.Success(endOfPaginationReached = body.isEmpty())
            } else {
                return MediatorResult.Error(Exception("API Error"))
            }
        } catch (exception: Exception) {
            Log.e("RemoteMediator", "Error loading data", exception)
            return MediatorResult.Error(exception)
        }
    }
}