package com.greeny.tmdbmovies.data.model
import com.google.gson.annotations.SerializedName
import com.greeny.tmdbmovies.data.model.Movie

data class MovieList(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int

)