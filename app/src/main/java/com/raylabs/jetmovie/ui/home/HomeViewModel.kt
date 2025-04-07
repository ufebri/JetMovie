package com.raylabs.jetmovie.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.raylabs.jetmovie.core.domain.model.MoviesTV
import com.raylabs.jetmovie.core.domain.usecase.movietv.MovieTVUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(useCase: MovieTVUseCase) : ViewModel() {

    //val trending: Flow<PagingData<MoviesTV>> = useCase.getTrendingMovieTV().cachedIn(viewModelScope)
    val popular: Flow<PagingData<MoviesTV>> =
        useCase.getPopularMovieTV().flowOn(Dispatchers.IO).cachedIn(viewModelScope)

//    fun genre(mediaType: String): LiveData<Resource<List<GenreEntity>>> =
//        dataRepository.getGenre(mediaType)
//
//    fun getMovieByKeyword(keyword: String): LiveData<Resource<PagedList<DataMovieTVEntity>>> =
//        dataRepository.getMovieByKeyword(keyword)

}