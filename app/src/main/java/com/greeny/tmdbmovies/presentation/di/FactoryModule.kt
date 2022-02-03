package com.greeny.tmdbmovies.presentation.di

import android.app.Application
import com.greeny.tmdbmovies.domain.usecase.GetMoviesUseCase
import com.greeny.tmdbmovies.domain.usecase.GetSavedMoviesUseCase
import com.greeny.tmdbmovies.domain.usecase.GetSearchedMoviesUseCase
import com.greeny.tmdbmovies.domain.usecase.SaveMoviesUseCase
import com.greeny.tmdbmovies.presentation.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule{
    @Singleton
    @Provides
    fun provideViewModelFactory(
        application: Application,
        getMoviesUseCase: GetMoviesUseCase,
        getSearchedMoviesUseCase: GetSearchedMoviesUseCase,
        saveMoviesUseCase: SaveMoviesUseCase,
        getSavedMoviesUseCase: GetSavedMoviesUseCase
    ): MovieViewModelFactory{
        return MovieViewModelFactory(application,getMoviesUseCase,getSearchedMoviesUseCase,saveMoviesUseCase,getSavedMoviesUseCase)
    }
}