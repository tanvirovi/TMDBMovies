package com.greeny.tmdbmovies.presentation.di

import com.greeny.tmdbmovies.presentation.MovieAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun provideMovieAdapter(): MovieAdapter{
        return MovieAdapter()
    }
}