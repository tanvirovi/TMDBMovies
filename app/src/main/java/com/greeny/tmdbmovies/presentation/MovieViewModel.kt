package com.greeny.tmdbmovies.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.greeny.tmdbmovies.data.model.Movie
import com.greeny.tmdbmovies.data.model.MovieList
import com.greeny.tmdbmovies.data.util.Resource
import com.greeny.tmdbmovies.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.http.Query

class MovieViewModel(
    val app : Application,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getSearchedMoviesUseCase: GetSearchedMoviesUseCase,
    private val saveMoviesUseCase: SaveMoviesUseCase,
    private val getSavedMoviesUseCase: GetSavedMoviesUseCase,
    private val deleteSavedMovieUseCase: DeleteSavedMovieUseCase
): AndroidViewModel(app){
    val popularMovies : MutableLiveData<Resource<MovieList>> = MutableLiveData()

    fun getMovies(page : Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            popularMovies.postValue(Resource.Loading())
            if (isNetWorkAvailable(app)){
                val apiResult = getMoviesUseCase.execute(page)
                Log.e("asdasdasd",apiResult.toString())
                popularMovies.postValue(apiResult)
            }else{
                popularMovies.postValue(Resource.Error("internet is not available"))
            }
        }catch (e : Exception){
            Log.e("ex",e.toString())
            popularMovies.postValue(Resource.Error(e.message.toString()))
        }

    }

    private fun isNetWorkAvailable(context: Context):Boolean{
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    //searching for movies

    val searchedMovies : MutableLiveData<Resource<MovieList>> = MutableLiveData()

    fun searchMovies(
        searchQuery: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        searchedMovies.postValue(Resource.Loading())
        try {
            if(isNetWorkAvailable(app)){
                val response =  getSearchedMoviesUseCase.execute(
                    searchQuery
                )
                searchedMovies.postValue(response)
            }else{
                searchedMovies.postValue(Resource.Error("no internet connection"))
            }
        }catch (e : java.lang.Exception){
            searchedMovies.postValue(Resource.Error(e.message.toString()))

        }

    }
    //local data
    fun saveMovies(movie:Movie) = viewModelScope.launch {
        saveMoviesUseCase.execute(movie)
    }

    fun getSavedMovies() = liveData{
        getSavedMoviesUseCase.execute().collect{
            emit(it)
        }
    }

    fun deleteMovie(movie: Movie) = viewModelScope.launch {
        deleteSavedMovieUseCase.execute(movie)
    }

}