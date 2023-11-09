package com.wafflestudio.assignment4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BlankViewModel @Inject constructor(
    private var api : MyRestAPI
): ViewModel() {
    private val _movieList: MutableLiveData<List<DataMovie>> = MutableLiveData(listOf())
    val movieList : LiveData<List<DataMovie>> = _movieList

    suspend fun loadMovie(){
        var movieAnswer = api.getMovie(MyApplication.preferences.getToken("token", ""))
        _movieList.value = movieAnswer!!.results
    }
}