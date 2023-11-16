package com.example.assignment2

import android.provider.Settings

import android.widget.TextView
import androidx.lifecycle.ViewModel
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MyViewModel() :ViewModel() {
    var team: Boolean = true
    var isEnd: Boolean = false
    var count: Int = 0
    var record: Array<List<Boolean?>> = Array(9) { mutableListOf(null) }

    private val _resetText = MutableLiveData<String>()
    val resetText: LiveData<String> = _resetText

    private val _statusText = MutableLiveData<String>()
    var statusText:LiveData<String> = _statusText

    private val _board:MutableLiveData<List<Boolean?>> = MutableLiveData<List<Boolean?>>(List(9){null})
    val board:LiveData<List<Boolean?>> = _board

    var data = mutableListOf<MyMultiData>(MyMultiData.TypeB("게임 시작!"))

    private fun checkEnd() {
        val currentBoard = board.value ?: return

        // Check rows
        for (i in 0 until 3) {
            val row = i * 3
            if (currentBoard[row] != null && currentBoard[row] == currentBoard[row + 1] && currentBoard[row + 1] == currentBoard[row + 2]) {
                isEnd = true
            }
        }

        // Check columns
        for (i in 0 until 3) {
            if (currentBoard[i] != null && currentBoard[i] == currentBoard[i + 3] && currentBoard[i + 3] == currentBoard[i + 6]) {
                isEnd = true
            }
        }

        // Check diagonals
        if (currentBoard[0] != null && currentBoard[0] == currentBoard[4] && currentBoard[4] == currentBoard[8]) {
            isEnd = true
        }
        if (currentBoard[2] != null && currentBoard[2] == currentBoard[4] && currentBoard[4] == currentBoard[6]) {
            isEnd = true
        }
    }

    fun reset(){
        team = true
        isEnd = false
        count= 0
        record= Array(9) { mutableListOf(null) }
        _resetText.value = ""
        _statusText.value = ""
        _board.value = (List(9){null})
    }

    fun boardClick(num: Int) {
        val currentBoard = _board.value?.toMutableList() ?: mutableListOf()
        currentBoard[num] = team

        if (count < 9 && _board.value?.get(num) == null && !isEnd) {
            //status update
            _board.value = currentBoard
            record[count] = currentBoard
            count++
            team = !team

            checkEnd()

            //if Game End
            if(isEnd || count == 9){
                _resetText.value = "한번더"

                data.add(MyMultiData.TypeC(currentBoard,team,isEnd))

                if(!isEnd) _statusText.value = "무승부"
                else _statusText.value = "게임 오버"
            }
            //if Game not End
            else{
                data.add(MyMultiData.TypeA(currentBoard,count))
                if (team) _statusText.value = "O의 차례입니다"
                else _statusText.value = "X의 차례입니다"
            }
        }
    }
}