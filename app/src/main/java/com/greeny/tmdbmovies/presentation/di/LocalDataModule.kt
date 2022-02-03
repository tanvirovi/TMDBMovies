package com.greeny.tmdbmovies.presentation.di

import com.greeny.tmdbmovies.data.db.MovieDAO
import com.greeny.tmdbmovies.data.repository.datasource.MovieLocalDataSource
import com.greeny.tmdbmovies.data.repository.datasourceImpl.MovieLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Provides
    @Singleton
    fun provideLocalDataSource(movieDAO: MovieDAO): MovieLocalDataSource{
        return MovieLocalDataSourceImpl(movieDAO)
    }
}