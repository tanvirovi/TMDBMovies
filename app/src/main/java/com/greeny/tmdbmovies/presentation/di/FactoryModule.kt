package com.greeny.tmdbmovies.presentation.di

import android.app.Application
import com.greeny.tmdbmovies.domain.usecase.*
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
        getSavedMoviesUseCase: GetSavedMoviesUseCase,
        deleteSavedMovieUseCase: DeleteSavedMovieUseCase
    ): MovieViewModelFactory{
        return MovieViewModelFactory(application,getMoviesUseCase,getSearchedMoviesUseCase,saveMoviesUseCase,getSavedMoviesUseCase,deleteSavedMovieUseCase)
    }
}