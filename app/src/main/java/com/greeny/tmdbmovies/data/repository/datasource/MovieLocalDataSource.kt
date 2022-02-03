package com.greeny.tmdbmovies.data.repository.datasource

import com.greeny.tmdbmovies.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    suspend fun saveMovieToDB(movie: Movie)

    fun getSavedMovies():Flow<List<Movie>>
}