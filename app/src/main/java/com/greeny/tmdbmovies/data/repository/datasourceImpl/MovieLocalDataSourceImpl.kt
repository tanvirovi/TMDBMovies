package com.greeny.tmdbmovies.data.repository.datasourceImpl

import com.greeny.tmdbmovies.data.db.MovieDAO
import com.greeny.tmdbmovies.data.model.Movie
import com.greeny.tmdbmovies.data.repository.datasource.MovieLocalDataSource
import kotlinx.coroutines.flow.Flow

class MovieLocalDataSourceImpl(
    private val movieDAO: MovieDAO
) : MovieLocalDataSource{
    override suspend fun saveMovieToDB(movie: Movie) {
        movieDAO.insert(movie)
    }

    override fun getSavedMovies(): Flow<List<Movie>> {
        return movieDAO.getAllMovies()
    }

    override suspend fun deleteMoviesFromDB(movie: Movie) {
        movieDAO.deleteMovie(movie)
    }
}