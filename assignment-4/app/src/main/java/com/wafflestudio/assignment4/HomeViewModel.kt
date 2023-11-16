package com.wafflestudio.assignment4

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wafflestudio.assignment4.data.MainRepository
import com.wafflestudio.assignment4.lib.network.MovieRestApi
import com.wafflestudio.assignment4.lib.network.dto.MovieDetailDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val api: MovieRestApi,
    private val repository: MainRepository
): ViewModel() {

    suspend fun getMovieDetails(language: String, page: Int): List<MovieDetailDto>  {
        return repository.getPopularMovies(language, page)
    }

}