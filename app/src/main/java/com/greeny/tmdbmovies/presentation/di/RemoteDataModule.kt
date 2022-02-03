package com.greeny.tmdbmovies.presentation.di

import com.greeny.tmdbmovies.data.api.TMDBService
import com.greeny.tmdbmovies.data.repository.datasource.MovieRemoteDataSource
import com.greeny.tmdbmovies.data.repository.datasourceImpl.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule(){
    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(
        tmdbService: TMDBService
    ): MovieRemoteDataSource{
        return MovieRemoteDataSourceImpl(tmdbService)
    }
}