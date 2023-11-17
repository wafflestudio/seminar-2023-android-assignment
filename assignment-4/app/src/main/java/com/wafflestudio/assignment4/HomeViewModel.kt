package com.wafflestudio.assignment4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val api : MyRestAPI
) : ViewModel(){
    private val _movie = MutableLiveData<Data.Movies>()
    val movie:LiveData<Data.Movies> = _movie

    suspend fun fetchMovie(){
        var token = MyApplication.preference.getToken()
       _movie.value = api.getMovieInfo("Bearer $token")
    }

}