package com.wafflestudio.assignment5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyViewModelFactory(private val api: MovieApi):
ViewModelProvider.NewInstanceFactory(){
    fun <T: ViewModel? > create(modelclass: Class<T>): T = MainViewModel(api) as T
}