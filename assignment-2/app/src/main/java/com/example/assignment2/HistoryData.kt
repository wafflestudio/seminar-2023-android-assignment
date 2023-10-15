package com.example.assignment2

sealed class HistoryData(val viewType: ViewType) {
    data class TurnNum(val num: Int) : HistoryData(ViewType.TURN_NUM)
    data class BoardData(val board: List<Mark>) : HistoryData(ViewType.BOARD_DATA)
    data class Comment(val comment : String) : HistoryData(ViewType.COMMENT)
    enum class ViewType {
        TURN_NUM,
        BOARD_DATA,
        COMMENT
    }
}