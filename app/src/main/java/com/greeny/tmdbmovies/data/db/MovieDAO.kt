package com.greeny.tmdbmovies.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.greeny.tmdbmovies.data.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)
    @Query("SELECT * FROM popular_movies")
    fun getAllMovies(): Flow<List<Movie>>
}