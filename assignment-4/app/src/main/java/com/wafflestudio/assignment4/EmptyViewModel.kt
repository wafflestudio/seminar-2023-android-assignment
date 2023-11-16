package com.wafflestudio.assignment4

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmptyViewModel @Inject constructor(
    private val movieService: MyRestAPI
): ViewModel() {
    private val _movies: MutableLiveData<List<MovieData>> = MutableLiveData()
    val movies: LiveData<List<MovieData>> = _movies

    fun fetchMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = movieService.fetchPopularMovies(retrieveToken())
                _movies.postValue(listOf(response))
            } catch (e: Exception) {
                Log.e("EmptyViewModel", "Error fetching movies: ${e.message}")
            }
        }
    }


    private fun retrieveToken(): String {
        return "Bearer ${MyApplication.preferences.GetToken("token", "")}"
    }
}
