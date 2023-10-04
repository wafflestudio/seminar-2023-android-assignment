package com.example.assignment2

import android.content.Context
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.DrawerBinding

class MainViewModel : ViewModel() {
    var currentContext: Context? = null
    var turn : Boolean = true
    var count = 0
    var O_lst = mutableListOf<Int>()
    var X_lst = mutableListOf<Int>()
    var finished = false
    var conditionText = ""
    var items = mutableListOf<MainActivity.MyData>()
}