package com.greeny.tmdbmovies.presentation.di


import com.greeny.tmdbmovies.domain.repository.MovieRepository
import com.greeny.tmdbmovies.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideMovieUseCase(movieRepository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }
    @Provides
    @Singleton
    fun provideSearchedMovieUseCase(movieRepository: MovieRepository): GetSearchedMoviesUseCase {
        return GetSearchedMoviesUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideSaveMovieUseCase(movieRepository: MovieRepository): SaveMoviesUseCase {
        return SaveMoviesUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideGetSaveMovieUseCase(movieRepository: MovieRepository): GetSavedMoviesUseCase {
        return GetSavedMoviesUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteSavedMovieUseCase(movieRepository: MovieRepository): DeleteSavedMovieUseCase {
        return DeleteSavedMovieUseCase(movieRepository)
    }
}