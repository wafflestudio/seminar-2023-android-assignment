package com.example.assignment2

enum class Cell(val text: String) {
    O("O"), X("X"), EMPTY("")
}

object BoardChecker {
    private val winningPatterns = listOf(
        Triple(0, 1, 2),
        Triple(3, 4, 5),
        Triple(6, 7, 8),
        Triple(0, 3, 6),
        Triple(1, 4, 7),
        Triple(2, 5, 8),
        Triple(0, 4, 8),
        Triple(2, 4, 6)
    )

    fun isGameOver(board: List<Cell>): Boolean {
        return winningPatterns.any { (a, b, c) ->
            board[a] != Cell.EMPTY && board[a] == board[b] && board[b] == board[c]
        }
    }
}
