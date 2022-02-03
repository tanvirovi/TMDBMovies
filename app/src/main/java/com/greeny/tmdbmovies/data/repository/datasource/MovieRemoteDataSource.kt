package com.greeny.tmdbmovies.data.repository.datasource

import com.greeny.tmdbmovies.data.model.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {

    suspend fun getMovies(page : Int): Response<MovieList>
    suspend fun getSearchedMovies(searchQuery:String): Response<MovieList>

}