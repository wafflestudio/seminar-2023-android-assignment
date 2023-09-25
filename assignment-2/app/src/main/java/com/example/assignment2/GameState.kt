package com.example.assignment2

import androidx.annotation.ColorRes

enum class GameState(
    val titleText: String,
    val buttonText: String,
    @ColorRes val buttonColorRes: Int,
) {
    O("O의 차례입니다", "초기화", R.color.gray),
    X("X의 차례입니다", "초기화", R.color.gray),
    Draw("무승부!", "한판 더", R.color.blue),
    Over("게임 오버", "한판 더", R.color.blue),
}

fun getGameStateFromBoard(board: List<CellState>): GameState {
    return if (isGameOver(board)) GameState.Over
    else if (board.count { it != CellState.E } == 9) GameState.Draw
    else if (board.count { it != CellState.E } % 2 == 0) GameState.O
    else GameState.X
}