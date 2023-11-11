package com.wafflestudio.assignment4

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: MyRestAPI
) : ViewModel() {

    private val _isLoggedIn : MutableLiveData<String> = MutableLiveData( MyApplication.preferences.getToken("isLoggedIn"))
    val isLoggedIn : LiveData<String> = _isLoggedIn


    fun authenticate(apiKey : String){
        if(apiKey==null) return ;
        viewModelScope.launch(Dispatchers.IO){
            MyApplication.preferences.setToken("accessToken", apiKey)
            try{
                val response = api.authenticate()
                if(response.isSuccessful){
                    MyApplication.preferences.setToken("isLoggedIn", "true")
                    withContext(Dispatchers.Main) {
                        _isLoggedIn.value = MyApplication.preferences.getToken("isLoggedIn")
                    }
                    Log.d("authentication", "success")
                }
                else{
                    Log.d("authentication", "failure")
                }
            }
            catch(e : Exception){
                Log.d("error", e.message.toString())
            }
        }
    }

    fun logout(){
        MyApplication.preferences.setToken("accessToken", "")
        MyApplication.preferences.setToken("isLoggedIn", "false")
        _isLoggedIn.value = MyApplication.preferences.getToken("isLoggedIn")

    }


    private val _movies : MutableLiveData<List<MovieData>> = MutableLiveData(listOf())
    val movies : LiveData<List<MovieData>> = _movies

    fun fetchMovie(){
        viewModelScope.launch(Dispatchers.IO){
            try{
                val response = api.fetchMovie()
                withContext(Dispatchers.Main){ _movies.value = response.results}

            }
            catch(e : Exception){
                Log.d("error",e.message.toString())
            }
        }

    }



}