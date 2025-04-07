package com.raylabs.jetmovie.core.di

import com.raylabs.jetmovie.core.data.repository.MovieTVRepository
import com.raylabs.jetmovie.core.domain.repository.IMovieTVRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(movieTVRepository: MovieTVRepository): IMovieTVRepository
}