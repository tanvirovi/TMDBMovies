package com.greeny.tmdbmovies.domain.usecase

import com.greeny.tmdbmovies.data.util.Resource
import com.greeny.tmdbmovies.data.model.MovieList
import com.greeny.tmdbmovies.domain.repository.MovieRepository

class GetMoviesUseCase (private val movieRepository: MovieRepository){
    suspend fun execute(page : Int): Resource<MovieList> = movieRepository.getMovies(page)
}