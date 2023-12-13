package com.wafflestudio.assignment5

import android.util.Log
import androidx.lifecycle.ViewModel
import com.wafflestudio.assignment5.data.MovieRepository
import com.wafflestudio.assignment5.lib.network.api.MovieRestApi
import com.wafflestudio.assignment5.lib.network.dto.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val repository: MovieRepository
): ViewModel() {

    private val _movieDetails: MutableStateFlow<List<MovieDetail>> = MutableStateFlow(listOf())
    val movieDetails: StateFlow<List<MovieDetail>> = _movieDetails.asStateFlow()

    suspend fun getMovie(query: String): List<MovieDetail>{
        _movieDetails.value = repository.getSearchedMovies(query, 1)
        Log.d("ViewModel", "${_movieDetails.value}")
        return movieDetails.value
    }
}