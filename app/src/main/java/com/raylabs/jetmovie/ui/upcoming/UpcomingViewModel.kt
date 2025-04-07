package com.raylabs.jetmovie.ui.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.raylabs.jetmovie.core.domain.usecase.movietv.MovieTVUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel @Inject constructor(useCase: MovieTVUseCase) : ViewModel() {

    val upcoming = useCase.getUpComingMovieTV().cachedIn(viewModelScope)
}