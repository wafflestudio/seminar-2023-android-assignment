package com.example.assignment2

import android.util.Log

class MainModel {

//    private var dataList: MutableList<String> = MutableList(9) { 0 }
//    private var pastdataList: MutableList<BoardData> = mutableListOf()
//    private var teamMark = "O"
//    private var turnNumber = 0
//    private var gameStatus = "P"

    private val dataList: MutableList<ArrayList<Array<Int>>> = MutableList(9) { ArrayList() } // [ [turn], [board], [gameStat] ]

    enum class GameStats {
        O, X, P, D, ERROR;
        fun isGameFinished(): Boolean {
            return !(this==P)
        }
    }

    init {
        initGame()
    }

    fun fetchDataList(): MutableList<ArrayList<Array<Int>>> {
        return dataList.map { it.clone() as ArrayList<Array<Int>> }.toMutableList()
    }

    fun getTurn(): Int {

        for (i in 1..8) {
            if (dataList[i][0][0] == 0) {
                Log.d("M", "returned turn")
                return i
            }
        }

        return 9
    }
    fun getTeamMark(turn: Int): String {
        val turnValue = turn
        return when {
            (turnValue % 2 != 0) -> "O"
            else -> "X"
        }
    }

    fun getBoard(turn: Int): Array<Int> {
        return dataList[turn][1]
    }

    fun getGameStat(turn: Int): GameStats {
        val gameStat = dataList[turn][2][0]
        return when (gameStat) {
            0 -> GameStats.P
            1 -> GameStats.O
            2 -> GameStats.X
            3 -> GameStats.D
            else -> GameStats.ERROR
        }
    }

    fun updateTurn(turn: Int) {
        if (turn <= 9) {
            dataList[turn][0][0] = turn
        }
    }

    fun updateBoard(turn: Int, position: Int) {
        val teamMark = getTeamMark(turn)
        val board = getBoard(turn - 1).toMutableList() // 이전 턴의 보드를 복사하여 수정할 수 있게 변경

        when (teamMark) {
            "O" -> board[position] = 1
            "X" -> board[position] = 2
        }

        // dataList의 해당 위치에 업데이트된 보드를 설정
        val intArray: Array<Int> = board.toTypedArray()
        dataList[turn][1] = intArray
    }

    fun updateGameStat(turn:Int) {
        val board = getBoard(turn)
        dataList[turn][2] = Array<Int>(1){checkGameStat(board)}
    }

    fun checkGameStat(board: Array<Int>): Int {

        for (i in 0 until 3) {
            if (board[i*3] != 0 && board[i*3] == board[i*3+1] && board[i*3] == board[i*3+2]) {
                return board[i*3]

            }
            if (board[i] != 0 && board[i] == board[i+3] && board[i] == board[i+6]) {
                return board[i]
            }
        }

        if (board[0] != 0 && board[0] == board[4] && board[0] == board[8]) {
            return board[0]
        }

        if (board[2] != 0 && board[2] == board[5] && board[2] == board[6]) {
            return board[2]
        }

        if (board.all( { it != 0})) {
            return 3
        }

        return 0
    }

    fun initGame() {
        dataList.clear()
        for (i in 0 until 10) {
            val elementList = arrayListOf<Array<Int>>(arrayOf(), arrayOf(), arrayOf())
            elementList[0] = arrayOf(0)
            elementList[1] = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
            elementList[2] = arrayOf(0)
            dataList.add(elementList)
        }
    }

}