package com.greeny.tmdbmovies.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.greeny.tmdbmovies.data.model.Movie

@Database(
    entities = [Movie::class],
    version = 2,
    exportSchema = false
)
abstract class MovieDataBase: RoomDatabase() {
    abstract fun getMovieDAO(): MovieDAO
}