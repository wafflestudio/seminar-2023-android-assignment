package com.example.assignment2

import android.util.Log

class MainModel {

    private var dataList: MutableList<String> = MutableList(9) { " " }
    private var pastdataList: MutableList<BoardData> = mutableListOf()
    private var teamMark = "O"
    private var turnNumber = 0

    fun getdataList(): List<String> {
        return dataList.toList()
    }

    fun updatedataList(index: Int) {
        dataList[index] = teamMark
        turnNumber++
        Log.d("MainModel", "dataList updated: $dataList")
    }

    fun deletedataList(index: Int) {
        if (index in 0 until dataList.size) {
            dataList[index] = " "
        }
    }

    fun initdataList() {
        for (i in 0..8) {
            deletedataList(i)
        }
        teamMark = "O"
        turnNumber = 0
    }

    fun savedataList() {
        pastdataList.add(BoardData.TurnNumber(turnNumber))
        pastdataList.add(BoardData.BoardValue(dataList))
        pastdataList.add(BoardData.ButtonText("되돌아가기"))

    }

    fun fetchPastdataList(): List<BoardData> {
        return pastdataList
    }

    fun changeTeam() {
        if (teamMark == "O") {
            teamMark = "X"
        }
        else {
            teamMark = "O"
        }
    }


    fun checkGame(): String {

        for (i in 0 until 3) {
            if (dataList[i*3] != " " && dataList[i*3] == dataList[i*3+1] && dataList[i*3] == dataList[i*3+2]) {
                return dataList[i*3] + "의 승리입니다"
            }
            if (dataList[i] != " " && dataList[i] == dataList[i+3] && dataList[i] == dataList[i+6]) {
                return dataList[i] + "의 승리입니다"
            }
        }

        if (dataList[0] != " " && dataList[0] == dataList[4] && dataList[0] == dataList[8]) {
            return dataList[0] + "의 승리입니다"
        }

        if (dataList[2] != " " && dataList[2] == dataList[5] && dataList[2] == dataList[6]) {
            return dataList[2] + "의 승리입니다"
        }

        if (dataList.all( { it != " "})) {
            return "게임 오버"
        }

        return teamMark + " 의 차례입니다"
    }

}