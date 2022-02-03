package com.greeny.tmdbmovies.data.api

import com.greeny.tmdbmovies.BuildConfig
import com.greeny.tmdbmovies.data.model.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page")page:Int,@Query("api_key")apiKey:String = BuildConfig.API_KEY ): Response<MovieList>

    @GET("search/movie")
    suspend fun getSearchedMovies(@Query("query")query:String,@Query("api_key")apiKey:String = BuildConfig.API_KEY ): Response<MovieList>
}