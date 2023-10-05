package com.example.assignment2

import android.content.Context
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var currentContext: Context? = null
    var turn : Boolean = true
    var count = 0
    var O_lst = mutableListOf<Int>()
    var X_lst = mutableListOf<Int>()
    var finished = false
    var conditionText = "O의 차례입니다"
    var items = mutableListOf<MainActivity.MyData>()
}