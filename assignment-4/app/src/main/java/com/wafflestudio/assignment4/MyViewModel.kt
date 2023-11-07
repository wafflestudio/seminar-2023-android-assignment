package com.wafflestudio.assignment4

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private var api : MyRestAPI
): ViewModel() {

    fun login(key : String?) : Boolean {
        return true;
    }
}