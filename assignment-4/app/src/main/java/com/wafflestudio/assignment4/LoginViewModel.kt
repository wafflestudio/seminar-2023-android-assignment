package com.wafflestudio.assignment4

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val restApiService: RestAPI
) : ViewModel() {
    private var moviesList: MutableList<MovieInfo> = mutableListOf()
    var liveMoviesList: MutableLiveData<MutableList<MovieInfo>> = MutableLiveData()

    suspend fun performLogin(authKey: String?): Boolean {
        if (authKey == null) return false
        val response = restApiService.authentication("Bearer $authKey")
        return response.success
    }

    suspend fun fetchMovies() {
        try {
            val movies = restApiService.getmovies("Bearer ${MyApplication.prefs.getString("token", "")}")
            val topMovies = movies.result.take(5).toMutableList()
            moviesList.addAll(topMovies)
            liveMoviesList.value = moviesList
            Log.d("LoginViewModel", "Movies fetched: $moviesList")
        } catch (e: Exception) {
            Log.e("LoginViewModel", "Error fetching movies: ${e.message}")
        }
    }
}
