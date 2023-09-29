package com.example.assignment2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.abs

class MainViewModel: ViewModel() {
    private var _board = MutableLiveData<Array<Array<Int>>>(Array(3){ Array(3) {0} })
    val board: LiveData<Array<Array<Int>>> = _board
    fun whoWins(newBoard: Array<Array<Int>>): String{

        for (row in 0..2){
            when(newBoard[row].sum()){
                3 -> return "O"
                -3 -> return "X"
                else -> {}
            }
        }
        for (col in 0..2){
            var colSum = 0
            for(row in 0..2){
                colSum += newBoard[row][col]
            }
            when(colSum){
                3 -> return "O"
                -3 -> return "X"
                else-> {}
            }
        }

        for(step in 0..2 step 2){
            var diagonalSum = 0
            for(i in 0..2){
                diagonalSum+= newBoard[abs(step-i)][i]
            }
            when(diagonalSum){
                3 -> return "O"
                -3 -> return "X"
                else-> {}
            }
        }
        return "Not yet"
    }


}