package com.example.assignment2

import com.example.assignment2.BoardChecker.isGameOver

enum class Game (
    val titleText: String,
    val buttonText: String
){
    O("기록","초기화"),
    X("기록","초기화"),
    Draw("기록","초기화"),
    Over("기록","한판더")
}

fun getGameStateFromBoard(board: List<Cell>): Game {
    return if (isGameOver(board)) Game.Over
    else if (board.count { it != Cell.EMPTY } == 9) Game.Draw
    else if (board.count { it != Cell.EMPTY } % 2 == 0) Game.O
    else Game.X
}