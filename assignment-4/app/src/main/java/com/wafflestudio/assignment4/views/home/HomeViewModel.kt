package com.wafflestudio.assignment4.views.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wafflestudio.assignment4.network.MyRestAPI
import com.wafflestudio.assignment4.network.dto.movie.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val myRestAPI: MyRestAPI,
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie?>>(List(5) { null })
    val movies: LiveData<List<Movie?>> = _movies

    suspend fun loadTop5Movies() {
        val loaded = myRestAPI.getPopularMovies().movies.subList(0, 5)
        _movies.postValue(loaded)
    }
}