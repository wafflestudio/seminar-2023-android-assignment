package com.wafflestudio.assignment4

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Movie
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wafflestudio.assignment4.data.MainRepository
import com.wafflestudio.assignment4.data.PrefStorage
import com.wafflestudio.assignment4.lib.network.MovieRestApi
import com.wafflestudio.assignment4.lib.network.dto.MovieDetailDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val api: MovieRestApi,
    private val repository: MainRepository,
    private val storage: PrefStorage
): ViewModel() {

    private val _movieList = MutableLiveData<MutableList<MovieDetailDto>>()
    val movieList: LiveData<MutableList<MovieDetailDto>> get() = _movieList

    private var movieInfos = mutableListOf<MovieDetailDto>()



    suspend fun fetchMovieDetails(language: String, page: Int)  {
//        viewModelScope.launch(Dispatchers.IO) {
            Log.d("HVM", "비동기 시작")
            Log.d("HVM", "${storage.getStoredTag("apiKey")}")
            movieInfos = repository.getPopularMovies(language, page, "application/json", storage.getStoredTag("apiKey")).toMutableList()
            _movieList.postValue(movieInfos) // postValue: thread 상관없이 안정성 보장
            Log.d("HVM", "this is $movieInfos")
//        }

        Log.d("HVM", "mvl value is ${_movieList.value}")
    }

    suspend fun getMovieDetails(language: String, page: Int): List<MovieDetailDto> {

        fetchMovieDetails(language, page)


        return movieInfos
    }


    fun deleteApiKey() {
        storage.deleteStoredTag(storage.getStoredTag("apiKey"))
        storage.setStoredTag("loginPref", "false")
        Log.d("HVM", "loginPref is ${storage.getStoredTag("loginPref")}")
    }

}