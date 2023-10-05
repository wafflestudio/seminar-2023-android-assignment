package com.example.assignment2

sealed class BoardData(val viewType: ViewType) {
    data class TurnNumber(val num: Int) : BoardData(ViewType.TURN_NUMBER)
    data class BoardValue(val board: List<String>) : BoardData(ViewType.BOARD_VALUE)
    data class ButtonText(val btnText: String) : BoardData(ViewType.BUTTON_TEXT)

    enum class ViewType {
        TURN_NUMBER,
        BOARD_VALUE,
        BUTTON_TEXT
    }
}