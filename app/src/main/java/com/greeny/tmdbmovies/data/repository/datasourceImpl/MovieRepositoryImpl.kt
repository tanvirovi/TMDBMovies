package com.greeny.tmdbmovies.data.repository.datasourceImpl

import android.util.Log
import com.greeny.tmdbmovies.data.model.Movie
import com.greeny.tmdbmovies.data.model.MovieList
import com.greeny.tmdbmovies.data.repository.datasource.MovieLocalDataSource
import com.greeny.tmdbmovies.data.repository.datasource.MovieRemoteDataSource
import com.greeny.tmdbmovies.data.util.Resource
import com.greeny.tmdbmovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource

): MovieRepository {
    override suspend fun getMovies(page : Int): Resource<MovieList> {
        return respondToCall(movieRemoteDataSource.getMovies(page))
    }

    private fun respondToCall(response: Response<MovieList>): Resource<MovieList> {
        if(response.isSuccessful){
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        Log.i("RESPONSE", response.message())
        return Resource.Error(response.message())
    }

    override suspend fun getSearchedMovie(searchQuery: String): Resource<MovieList> {
        return respondToCall(
            movieRemoteDataSource.getSearchedMovies(searchQuery)
        )
    }

    override suspend fun saveMovie(movie: Movie) {
        movieLocalDataSource.saveMovieToDB(movie)
    }

    override fun getSavedMovies(): Flow<List<Movie>> {
        return movieLocalDataSource.getSavedMovies()
    }
}