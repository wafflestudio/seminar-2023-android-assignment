package com.example.movie

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val api:MyRestAPI,
): ViewModel(){
    fun checkAPIKey(key:String){
        viewModelScope.launch(Dispatchers.IO){
            val response=api.checkAPIKey(key)
            Log.d("aaaa",response.toString())
        }
    }
    fun printOne(){
        Log.d("aaaa","1")
    }
}