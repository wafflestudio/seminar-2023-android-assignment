package com.wafflestudio.assignment4

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: MovieApi
): ViewModel() {
    suspend fun tryLogin(key: String?){
        var response = api.getLogin("Bearer " + key)
    }
}