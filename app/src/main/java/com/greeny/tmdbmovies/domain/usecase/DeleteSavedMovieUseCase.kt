package com.greeny.tmdbmovies.domain.usecase

import com.greeny.tmdbmovies.data.model.Movie
import com.greeny.tmdbmovies.domain.repository.MovieRepository

class DeleteSavedMovieUseCase(private val movieRepository: MovieRepository){
    suspend fun execute(movie: Movie) = movieRepository.deleteMovie(movie)
}