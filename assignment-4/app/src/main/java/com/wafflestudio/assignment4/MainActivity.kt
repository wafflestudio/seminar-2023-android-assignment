package com.wafflestudio.assignment4

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: RestAPI
) : ViewModel() {
    var movieListLiveData: MutableLiveData<MutableList<MovieInfo>> = MutableLiveData()
    private var movieDataList: MutableList<MovieInfo> = mutableListOf()

    suspend fun loginAttempt(authenticationKey: String?): Boolean {
        if (authenticationKey.isNullOrEmpty()) return false
        val loginResponse = apiService.authentication("Bearer $authenticationKey")
        return loginResponse.success
    }

    suspend fun loadMovieData() {
        try {
            val moviesResponse = apiService.getmovies("Bearer ${MyApplication.prefs.getString("token", "")}")
            val selectedMovies = moviesResponse.result.take(5).toMutableList()
            movieDataList.addAll(selectedMovies)
            movieListLiveData.value = movieDataList
            Log.i("LoginViewModel", "Movies loaded: $movieDataList")
        } catch (exception: Exception) {
            Log.e("LoginViewModel", "Error in loading movies: ${exception.localizedMessage}")
        }
    }
}
