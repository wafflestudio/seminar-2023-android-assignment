package com.example.assignment2

import android.provider.Settings

import android.widget.TextView
import androidx.lifecycle.ViewModel
import android.content.Context

class MyViewModel() :ViewModel() {
    var team: Boolean = true;
    var isEnd: Boolean = false;
    var record: Array<Array<Array<Boolean?>>> = Array(9) { Array(3) { Array(3) { null } } }
    var board: Array<Array<Boolean?>> = Array(3) { Array(3) { null } }
    var count: Int = 0

    var data = mutableListOf<MyMultiData>(MyMultiData.TypeB("게임 시작!"))

    fun basicSet(column: Int, row: Int, block: TextView){
        if(board[column][row] == true) block.text = "X"
        else if(board[column][row] == false) block.text = "O"
    }

    private fun checkEnd() {
        // Check rows
        for (i in 0 until 3) {
            if (board[i][0] != null && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                isEnd = true
            }
        }

        // Check columns
        for (i in 0 until 3) {
            if (board[0][i] != null && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                isEnd = true
            }
        }

        // Check diagonals
        if (board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            isEnd = true
        }
        if (board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            isEnd = true
        }
    }

    fun boardClick(column: Int, row: Int, block: TextView, reset: TextView, status: TextView) {
        if (count < 9 && board[column][row] == null && !isEnd) {
            board[column][row] = team
            record[count] = board
            count++
            team = !team

            data.add(MyMultiData.TypeA(board,count))

            if (team) block.text = "O"
            else block.text = "X"

            checkEnd()

            if (isEnd) reset.text = "한번더"

            if (isEnd || count == 9) {
                if (!isEnd) status.text = "무승부"
                else status.text = "게임 오버"
            } else {
                if (team) status.text = "X의 차례입니다"
                else status.text = "O의 차례입니다"
            }
        }
    }
}