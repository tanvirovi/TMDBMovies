package com.greeny.tmdbmovies.domain.repository

import com.greeny.tmdbmovies.data.util.Resource
import com.greeny.tmdbmovies.data.model.Movie
import com.greeny.tmdbmovies.data.model.MovieList
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovies(page : Int): Resource<MovieList>
    suspend fun getSearchedMovie(searchQuery : String): Resource<MovieList>
    suspend fun saveMovie(movie: Movie)
    fun getSavedMovies():Flow<List<Movie>>

}