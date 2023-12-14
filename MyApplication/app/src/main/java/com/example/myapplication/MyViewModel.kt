package com.example.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MyViewModel @Inject constructor(
    private val api:MyRestAPI,
): ViewModel(){
    fun printOne(){
        Log.d("aaaa","1")
    }
}