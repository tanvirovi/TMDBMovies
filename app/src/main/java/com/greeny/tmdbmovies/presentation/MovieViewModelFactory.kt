package com.greeny.tmdbmovies.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.greeny.tmdbmovies.domain.usecase.*

class MovieViewModelFactory(
    private val app: Application,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getSearchedMoviesUseCase: GetSearchedMoviesUseCase,
    private val saveMoviesUseCase: SaveMoviesUseCase,
    private val getSavedMoviesUseCase: GetSavedMoviesUseCase,
    private val deleteSavedMovieUseCase: DeleteSavedMovieUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieViewModel(
            app,
            getMoviesUseCase,
            getSearchedMoviesUseCase,
            saveMoviesUseCase, getSavedMoviesUseCase,
            deleteSavedMovieUseCase
        ) as T
    }
}