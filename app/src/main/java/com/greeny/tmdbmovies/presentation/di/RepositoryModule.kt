package com.greeny.tmdbmovies.presentation.di


import com.greeny.tmdbmovies.data.repository.datasource.MovieLocalDataSource
import com.greeny.tmdbmovies.data.repository.datasource.MovieRemoteDataSource
import com.greeny.tmdbmovies.data.repository.datasourceImpl.MovieRemoteDataSourceImpl
import com.greeny.tmdbmovies.data.repository.datasourceImpl.MovieRepositoryImpl
import com.greeny.tmdbmovies.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource
    ): MovieRepository{
        return MovieRepositoryImpl(movieRemoteDataSource,movieLocalDataSource)
    }
}