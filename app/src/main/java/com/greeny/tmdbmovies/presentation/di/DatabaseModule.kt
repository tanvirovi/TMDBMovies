package com.greeny.tmdbmovies.presentation.di

import android.app.Application
import androidx.room.Room
import com.greeny.tmdbmovies.data.db.MovieDAO
import com.greeny.tmdbmovies.data.db.MovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun providerMovieDatabase(app: Application): MovieDataBase{
        return Room.databaseBuilder(
            app,
            MovieDataBase::class.java,
            "Movie_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideMovieDao(movieDataBase: MovieDataBase): MovieDAO{
        return movieDataBase.getMovieDAO()
    }
}