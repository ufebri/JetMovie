package com.raylabs.jetmovie.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.raylabs.jetmovie.core.data.source.local.entity.mapToDomain
import com.raylabs.jetmovie.core.data.source.local.room.JetMovieDao
import com.raylabs.jetmovie.core.domain.model.MoviesTV

class MovieTVLocalPagingSource(private val dao: JetMovieDao, private val sourceData: String) :
    PagingSource<Int, MoviesTV>() {

    override fun getRefreshKey(state: PagingState<Int, MoviesTV>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesTV> {
        return try {
            when (val mData = dao.getAllMovie(sourceData).load(params)) {
                is LoadResult.Page -> LoadResult.Page(
                    data = mData.data.map { it.mapToDomain() },
                    prevKey = mData.prevKey,
                    nextKey = mData.nextKey
                )

                is LoadResult.Error -> LoadResult.Error(mData.throwable)
                is LoadResult.Invalid<*, *> -> LoadResult.Invalid()
            }
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}