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
class BlankViewModel @Inject constructor(
    private var api : MyRestAPI
): ViewModel() {
    private val _movieList: MutableLiveData<List<DataMovie>> = MutableLiveData(listOf())
    val movieList : LiveData<List<DataMovie>> = _movieList

    suspend fun loadMovie(){
        //Log.d("token", MyApplication.preferences.getToken("token", ""))
        var movieAnswer =
            api.getMovie("Bearer " + MyApplication.preferences.getToken("token", ""))
        //Log.d("api", movieAnswer.total_results.toString())
        _movieList.value = movieAnswer.results
    }
}