package com.example.assignment2

enum class CellState(val text: String) {
    O("O"), X("X"), E(""),
}

fun isGameOver(board: List<CellState>): Boolean {
    return MainViewModel.EndList.any { (a, b, c) ->
        board[a] != CellState.E && board[a] == board[b] && board[b] == board[c]
    }
}