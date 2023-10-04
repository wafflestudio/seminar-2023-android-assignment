package com.example.assignment2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    var team = 1
    var liveTurn: MutableLiveData<Int> = MutableLiveData(1)
    fun turnAdd(num: Int) {
        liveTurn.value = (liveTurn.value)?.plus(num)
    }

    var liveGameStatus: MutableLiveData<MainActivity.Status> = MutableLiveData(MainActivity.Status.CONTINUE)

    // board
    var liveBoard: MutableLiveData<IntArray> = MutableLiveData()
    var board = IntArray(10) { 0 }
    fun boardInit() {
        board[0] = -1
        for (i in 1..9) board[i] = 0
        liveBoard.value = board
    }
    fun changeBoard(index: Int) {
        board[index] = team
        liveBoard.value = board
    }

    // drawer
    val boardData = mutableListOf<MyMultiData>(
        MyMultiData.GameStart(1),
    )

}