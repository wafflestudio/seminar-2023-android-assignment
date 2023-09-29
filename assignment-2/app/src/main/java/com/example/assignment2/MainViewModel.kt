package com.example.assignment2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.abs


class MainViewModel: ViewModel() {
    private var turn = 1
    private var flag = 0 // 게임 종료 여부
    private val board = Array(3){ Array(3) {0} }
    private val _boardLiveData = MutableLiveData<Array<Array<Int>>>()
    val boardLiveData : LiveData<Array<Array<Int>>> = _boardLiveData
    init{
        _boardLiveData.value = board
    }
    fun whoWins(next: Int): String{
        val index = next - 1
        if(flag != 0) return "Game over"
        if(board[index / 3][index % 3] != 0) return "Click another cell"
        board[index / 3][index % 3] = turn
        _boardLiveData.value = board
        for (row in 0..2){
            when(board[row].sum()){
                3 -> {
                    flag = 1
                    return "O wins"
                }
                -3 -> {
                    flag = -1
                    return "X wins"
                }
                else -> {}
            }
        }
        for (col in 0..2){
            var colSum = 0
            for(row in 0..2){
                colSum += board[row][col]
            }
            when(colSum){
                3 -> {
                flag = 1
                return "O wins"
            }
                -3 -> {
                    flag = -1
                    return "X wins"
                }
                else-> {}
            }
        }

        for(step in 0..2 step 2){
            var diagonalSum = 0
            for(i in 0..2){
                diagonalSum+= board[abs(step-i)][i]
            }
            when(diagonalSum){
                3 -> {
                flag = 1
                return "O wins"
            }
                -3 -> {
                    flag = -1
                    return "X wins"
                }
                else-> {}
            }
        }
        turn *= -1
        when(turn){
            -1 -> return "its X turn now"
            1 -> return "its O turn now"
        }
        return "Error"
    }


}