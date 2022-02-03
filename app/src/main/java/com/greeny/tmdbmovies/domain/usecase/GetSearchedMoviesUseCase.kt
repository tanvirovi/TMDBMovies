package com.greeny.tmdbmovies.domain.usecase

import com.greeny.tmdbmovies.data.util.Resource
import com.greeny.tmdbmovies.data.model.Movie
import com.greeny.tmdbmovies.data.model.MovieList
import com.greeny.tmdbmovies.domain.repository.MovieRepository

class GetSearchedMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(searchQuery: String): Resource<MovieList> = movieRepository.getSearchedMovie(searchQuery)
}