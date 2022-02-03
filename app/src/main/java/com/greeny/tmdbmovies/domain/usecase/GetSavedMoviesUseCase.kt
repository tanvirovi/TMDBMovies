package com.greeny.tmdbmovies.domain.usecase

import com.greeny.tmdbmovies.data.model.Movie
import com.greeny.tmdbmovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetSavedMoviesUseCase(private val movieRepository: MovieRepository) {
    fun execute(): Flow<List<Movie>>{
        return movieRepository.getSavedMovies()
    }
}