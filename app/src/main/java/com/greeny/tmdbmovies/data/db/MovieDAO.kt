package com.greeny.tmdbmovies.data.db

import androidx.room.*
import com.greeny.tmdbmovies.data.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM popular_movies")
    fun getAllMovies(): Flow<List<Movie>>

    @Delete
    suspend fun deleteMovie(movie: Movie)
}