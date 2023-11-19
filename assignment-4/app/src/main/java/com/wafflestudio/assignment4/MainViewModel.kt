package com.wafflestudio.assignment4

import android.app.Application
import android.graphics.Movie
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: MovieApi
): ViewModel() {
    companion object{}
    private val _loginStatus = MutableLiveData<String>()
    val loginStatus : LiveData<String> = _loginStatus
    suspend fun tryLogin(key: String?){
        val response = api.getLogin("Bearer " + key.toString())
        if(response.success){
            withContext(Dispatchers.Main) {
                Myapplication.preferences.putToken("Token",key.toString())
                Myapplication.preferences.putToken("LoginStatus", "true")
                _loginStatus.value = "true"
            }
        }
    }
    fun logout(){
        Myapplication.preferences.removeToken("Token")
        Myapplication.preferences.removeToken("LoginStatus")
        _loginStatus.value = "false"
    }

    private val _movieList = MutableLiveData<List<MovieData>>()
    val movieList: LiveData<List<MovieData>> = _movieList
    suspend fun getMovie(key: String?): List<MovieData>{
        val response = api.getMovie("Bearer " + key)
        _movieList.postValue(response.results)
        return response.results
    }

}