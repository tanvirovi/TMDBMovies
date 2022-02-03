package com.greeny.tmdbmovies.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.greeny.tmdbmovies.domain.usecase.GetMoviesUseCase
import com.greeny.tmdbmovies.domain.usecase.GetSavedMoviesUseCase
import com.greeny.tmdbmovies.domain.usecase.GetSearchedMoviesUseCase
import com.greeny.tmdbmovies.domain.usecase.SaveMoviesUseCase

class MovieViewModelFactory(
    private val app: Application,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getSearchedMoviesUseCase: GetSearchedMoviesUseCase,
    private val saveMoviesUseCase: SaveMoviesUseCase,
    private val getSavedMoviesUseCase: GetSavedMoviesUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(
            app,
            getMoviesUseCase,
            getSearchedMoviesUseCase,
            saveMoviesUseCase, getSavedMoviesUseCase
        ) as T
    }
}