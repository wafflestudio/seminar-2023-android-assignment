package com.example.assignment2

import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    var recyclerview_data=mutableListOf(
        MyMultiData.TypeA(emptyList<Int>().toMutableList())
    )
    var game_data= mutableListOf(emptyList<Int>().toMutableList())
}