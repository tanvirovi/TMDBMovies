package com.greeny.tmdbmovies.data.repository.datasourceImpl


import com.greeny.tmdbmovies.data.api.TMDBService
import com.greeny.tmdbmovies.data.model.MovieList
import com.greeny.tmdbmovies.data.repository.datasource.MovieRemoteDataSource
import retrofit2.Response

class MovieRemoteDataSourceImpl (
    private val tmDbService: TMDBService,
    ): MovieRemoteDataSource {
    override suspend fun getMovies(page : Int): Response<MovieList> {
        return tmDbService.getPopularMovies(page)
    }

    override suspend fun getSearchedMovies(searchQuery: String): Response<MovieList> {
        return tmDbService.getSearchedMovies(searchQuery)
    }

}