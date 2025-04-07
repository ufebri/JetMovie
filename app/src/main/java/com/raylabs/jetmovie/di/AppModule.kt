package com.raylabs.jetmovie.di

import com.raylabs.jetmovie.core.domain.usecase.movietv.MovieTVInteractor
import com.raylabs.jetmovie.core.domain.usecase.movietv.MovieTVUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideMovieTVUseCase(movieTVInteractor: MovieTVInteractor): MovieTVUseCase
}